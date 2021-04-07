package com.pillgnal.backend.controller;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.*;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.family.ConnectResponseDto;
import com.pillgnal.backend.dto.pill.PillListResponseDto;
import com.pillgnal.backend.dto.prescription.PrescriptionCreateRequestDto;
import com.pillgnal.backend.dto.prescription.PrescriptionListResponseDto;
import com.pillgnal.backend.dto.prescription.PrescriptionPillDetailResponseDto;
import com.pillgnal.backend.service.PillService;
import com.pillgnal.backend.service.PrescriptionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

/**
 * 처방전 관련 Controller
 *
 * @author Eomjaewoong
 */
@Api(value = "처방전 관련")
@RequiredArgsConstructor
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final PillService pillService;
    static String pill_list = "";

    /**
     * 처방전 만들기
     * @param createRequest
     * @return ResponseEntity
     * @author Eomjaewoong
     */
    @ApiOperation(value = "처방전 만들기 요청")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED - 처방전 만들기 성공"),
            @ApiResponse(code = 400, message = "조회 실패")
    })
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<ResponseDto> onCreatePrescription(@RequestBody PrescriptionCreateRequestDto createRequest) {
        ResponseDto response = prescriptionService.doCreatePrescription(createRequest);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 처방전 리스트 요청 처리
     *
     * @param email
     * @return ConnectResponseDto
     * @author Eomjaewoong
     */
    @ApiOperation(value = "처방전 리스트 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 리스트 조회 성공"),
            @ApiResponse(code = 400, message = "조회 실패")
    })
    @GetMapping(value = "/{email}/")
    public ResponseEntity<ConnectResponseDto> onListPrescription(@PathVariable String email) {
        PrescriptionListResponseDto response = prescriptionService.doListPrescription(email);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 처방전 상세 내역 요청 처리
     *
     * @param email
     * @return ConnectResponseDto
     * @author Eomjaewoong
     */
    @ApiOperation(value = "처방전 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 처방전 조회 성공"),
            @ApiResponse(code = 400, message = "조회 실패")
    })
    @GetMapping(value = "/{email}/{prid}")
    public ResponseEntity<PrescriptionPillDetailResponseDto> onDetailPrescription(@PathVariable String email, @PathVariable Long prid) {
        PrescriptionPillDetailResponseDto response = prescriptionService.doDetailPrescription(email, prid);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 처방전 스캔 요청 처리
     *
     * @param file
     * @return ResponseEntity
     * @throws IOException
     * @author Kimjaeseung
     */
    @ApiOperation(value = "처방전 ocr", notes = "처방전 ocr 후 알약 이름만 반환", response = Map.class)
    @PostMapping(value = "/ocr")
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
                String[] splitstr = msg.split("/");
                for (int i = 0; i < splitstr.length; i++) {
                    list.add(splitstr[i]);
                }
                map.put("처방전에서 인식한 알약:", msg);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        PillListResponseDto response = pillService.doFindPillByPre(list);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 구글 스캔
     *
     * @param gcsPath
     * @param map
     * @throws IOException
     * @author Kimjaeseung
     */
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

    /**
     * gcs에 파일 업로드
     *
     * @param file
     * @return
     * @throws IOException
     * @author Kimjaeseung
     */
    @ApiOperation(value = "gcs에 파일 업로드 테스트", notes = "'SUCCESS' 또는 'FAIL' 문자열을 리턴", response = Map.class)
    @PostMapping("/gcs_fileupload_test")
    public String uploadFile(@RequestBody MultipartFile file) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        try {
            System.out.println(file.getOriginalFilename());
            BlobInfo blobInfo = storage.create(BlobInfo.newBuilder("ocrtestttt", file.getOriginalFilename()).build(), // get																					// name
                    file.getBytes(), // the file
                    Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ) // Set file permission
            );
            return blobInfo.getMediaLink(); // Return file url
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}
