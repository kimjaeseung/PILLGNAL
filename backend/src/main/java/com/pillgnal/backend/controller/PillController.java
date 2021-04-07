package com.pillgnal.backend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.pillgnal.backend.dto.pill.PillListResponseDto;
import com.pillgnal.backend.domain.pill.PillRepository;
import com.pillgnal.backend.service.PillService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobTargetOption;
import com.google.cloud.storage.Storage.PredefinedAcl;
import com.google.cloud.storage.StorageOptions;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class PillController {
	static String pill_list;
	private final PillRepository pillRepository;
	private final PillService pillService;
	
	@ApiOperation(value = "파이썬 소켓 통신 알약 모양, 문자 확인", notes = "socket 열고 python 통신해서 image 받아옴", response = Map.class)
	@PostMapping(value = "/api/v1/pill")
	public ResponseEntity<PillListResponseDto> pill(@RequestBody MultipartFile file) throws IOException {
		pill_list = null;
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		uploadFile(file);
		String temp = "https://storage.googleapis.com/ocrtestttt/";
		String publicurl = temp + file.getOriginalFilename();
		String temp2 = "gs://ocrtestttt/";
		String gsutilurl = temp2 + file.getOriginalFilename();

		try {
			detectTextGcs(gsutilurl, map);
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		String msg = null;
		try (Socket client = new Socket()) {
			// 소켓에 접속하기 위한 접속 정보를 선언한다.
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
			client.connect(ipep);
			try (OutputStream sender = client.getOutputStream(); InputStream receiver = client.getInputStream();) {
				msg = publicurl;
				byte[] data = msg.getBytes();
				ByteBuffer b = ByteBuffer.allocate(4);
				b.order(ByteOrder.LITTLE_ENDIAN);
				b.putInt(data.length);
				sender.write(b.array(), 0, 4);
				// 데이터 전송
				sender.write(data);
				data = new byte[4];
				receiver.read(data, 0, 4);
				ByteBuffer c = ByteBuffer.wrap(data);
				c.order(ByteOrder.LITTLE_ENDIAN);
				int length = c.getInt();
				data = new byte[length];
				receiver.read(data, 0, length);
				msg = new String(data, "UTF-8");
				System.out.println(msg);
				map.put("알약 모양", msg);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PillListResponseDto response = pillService.doFindPillByPill(pill_list, msg);
		return new ResponseEntity(response, response.isSuccess()? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "처방전 ocr", notes = "처방전 ocr 후 알약 이름만 반환", response = Map.class)
	@PostMapping(value = "/api/v1/prescription")
	public ResponseEntity<PillListResponseDto> ocrtest(
			@ApiParam(value = "filePath", required = true) @RequestBody MultipartFile file) throws IOException {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		List<String> list = new ArrayList<String>();
		pill_list = null;
		uploadFile(file);
		
		String temp = "https://storage.googleapis.com/ocrtestttt/";
		String publicurl = temp + file.getOriginalFilename();
		String temp2 = "gs://ocrtestttt/";
		String gsutilurl = temp2 + file.getOriginalFilename();
		try {
			detectTextGcs(gsutilurl, map);
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try (Socket client = new Socket()) {
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
			client.connect(ipep);
			try (OutputStream sender = client.getOutputStream(); InputStream receiver = client.getInputStream();) {
				String msg = pill_list;
				byte[] data = msg.getBytes();
				ByteBuffer b = ByteBuffer.allocate(4);
				b.order(ByteOrder.LITTLE_ENDIAN);
				b.putInt(data.length);
				sender.write(b.array(), 0, 4);
				// 데이터 전송
				sender.write(data);
				data = new byte[4];
				receiver.read(data, 0, 4);
				ByteBuffer c = ByteBuffer.wrap(data);
				c.order(ByteOrder.LITTLE_ENDIAN);
				int length = c.getInt();
				data = new byte[length];
				receiver.read(data, 0, length);
				msg = new String(data, "UTF-8");
				System.out.println(msg);
				String [] splitstr = msg.split("/");
				for (int i = 0; i < splitstr.length; i++) {
					list.add(splitstr[i]);
				}
				map.put("처방전에서 인식한 알약:", msg);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PillListResponseDto response = pillService.doFindPillByPre(list);
		return new ResponseEntity(response, response.isSuccess()? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "구글비전 ocr test", notes = "이미지 넣으면 ocr 테스트해줌", response = Map.class)
	@GetMapping(value = "/api/v1/google_vision_ocr_api_test")
	public ResponseEntity<Map<String, String>> ocrtest(
			@ApiParam(value = "filePath", required = true) @RequestParam String filePath) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			detectTextGcs(filePath, map);
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	public static void detectTextGcs(String gcsPath, Map<String, String> map) throws IOException {
		List<AnnotateImageRequest> requests = new ArrayList<>();
		ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
		Image img = Image.newBuilder().setSource(imgSource).build();
		Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(request);
		
		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
					return;
				}
				EntityAnnotation annotation = res.getTextAnnotations(0);
				String temp = annotation.getDescription();
				pill_list = temp.replaceAll("\n", "/");
				map.put("인식 문자:", pill_list);
				System.out.println(annotation.getDescription());
			}
		}
	}
	/*
	@ApiOperation(value = "파이썬 소켓 통신 알약 모양, 문자 확인", notes = "socket 열고 python 통신해서 image 받아옴", response = Map.class)
	@PostMapping(value = "/api/v1/pill")
	public ResponseEntity<Map<String, String>> pill(
			@ApiParam(value = "filePath", required = true) @RequestBody MultipartFile file) throws IOException {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		uploadFile(file);
		String temp = "https://storage.googleapis.com/ocrtestttt/";
		String publicurl = temp + file.getOriginalFilename();
		String temp2 = "gs://ocrtestttt/";
		String gsutilurl = temp2 + file.getOriginalFilename();
		System.out.println(publicurl);
		try {
			detectTextGcs(gsutilurl, map);
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try (Socket client = new Socket()) {
			// 소켓에 접속하기 위한 접속 정보를 선언한다.
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
			client.connect(ipep);
			try (OutputStream sender = client.getOutputStream(); InputStream receiver = client.getInputStream();) {
				String msg = publicurl;
				byte[] data = msg.getBytes();
				ByteBuffer b = ByteBuffer.allocate(4);
				b.order(ByteOrder.LITTLE_ENDIAN);
				b.putInt(data.length);
				sender.write(b.array(), 0, 4);
				// 데이터 전송
				sender.write(data);
				data = new byte[4];
				receiver.read(data, 0, 4);
				ByteBuffer c = ByteBuffer.wrap(data);
				c.order(ByteOrder.LITTLE_ENDIAN);
				int length = c.getInt();
				data = new byte[length];
				receiver.read(data, 0, length);
				msg = new String(data, "UTF-8");
				System.out.println(msg);
				map.put("알약 모양", msg);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	 */
	@ApiOperation(value = "gcs에 파일 업로드 테스트", notes = "'SUCCESS' 또는 'FAIL' 문자열을 리턴", response = Map.class)
	@PostMapping("/api/v1/gcs_fileupload_test")
	public String uploadFile(@RequestBody MultipartFile file) throws IOException {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		try {
			System.out.println(file.getOriginalFilename());
			BlobInfo blobInfo = storage.create(BlobInfo.newBuilder("ocrtestttt", file.getOriginalFilename()).build(), // get																					// name
					file.getBytes(), // the file
					BlobTargetOption.predefinedAcl(PredefinedAcl.PUBLIC_READ) // Set file permission
			);
			return blobInfo.getMediaLink(); // Return file url
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	@ApiOperation(value = "처방전 ocr", notes = "처방전 ocr 후 알약 이름만 반환", response = Map.class)
	@PostMapping(value = "/api/v1/prescription")
	public ResponseEntity<Map<String, String>> ocrtest(
			@ApiParam(value = "filePath", required = true) @RequestBody MultipartFile file) throws IOException {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		List<String> list = new ArrayList<String>();
		uploadFile(file);
		String temp = "https://storage.googleapis.com/ocrtestttt/";
		String publicurl = temp + file.getOriginalFilename();
		String temp2 = "gs://ocrtestttt/";
		String gsutilurl = temp2 + file.getOriginalFilename();
		System.out.println(publicurl);
		try {
			detectTextGcs(gsutilurl, map);
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try (Socket client = new Socket()) {
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
			client.connect(ipep);
			try (OutputStream sender = client.getOutputStream(); InputStream receiver = client.getInputStream();) {
				String msg = pill_list;
				byte[] data = msg.getBytes();
				ByteBuffer b = ByteBuffer.allocate(4);
				b.order(ByteOrder.LITTLE_ENDIAN);
				b.putInt(data.length);
				sender.write(b.array(), 0, 4);
				// 데이터 전송
				sender.write(data);
				data = new byte[4];
				receiver.read(data, 0, 4);
				ByteBuffer c = ByteBuffer.wrap(data);
				c.order(ByteOrder.LITTLE_ENDIAN);
				int length = c.getInt();
				data = new byte[length];
				receiver.read(data, 0, length);
				msg = new String(data, "UTF-8");
				System.out.println(msg);
				String [] splitstr = msg.split("/");
				for (int i = 0; i < splitstr.length; i++) {
					list.add(splitstr[i]);
				}
				map.put("처방전에서 인식한 알약:", msg);
				
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		pill_list = null;
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	*/
}
