package com.pillgnal.backend.controller;

import com.pillgnal.backend.domain.example.Example;
import com.pillgnal.backend.domain.example.ExampleRepository;
import com.pillgnal.backend.dto.ExampleSaveRequestDto;
import com.pillgnal.backend.dto.ExampleUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ExampleRepository exampleRepository;

    @After
    public void tearDown() throws Exception {
        exampleRepository.deleteAll();
    }

    /**
     * 등록 테스트
     *
     * @author Eomjaewoong
     */
    @Test
    public void registPost() throws Exception {
        // given
        String title = "title";
        String content = "content";
        ExampleSaveRequestDto requestDto = ExampleSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Example> all = exampleRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    /**
     * 수정 테스트
     *
     * @author Eomjaewoong
     */
    @Test
    public void modifyPost() throws Exception {
        // given
        Example example = exampleRepository.save(Example.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = example.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        ExampleUpdateRequestDto requestDto = ExampleUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;
        HttpEntity<ExampleUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Example> all = exampleRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

    /**
     * 조회 테스트
     *
     * @author Eomjaewoong
     */
    @Test
    public void findOneExample() {
        // given
        Example example = exampleRepository.save(Example.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long findId = example.getId();

        String url = "http://localhost:"+port+"/api/v1/posts/"+findId;

        // when
        ResponseEntity<Example> responseEntity = restTemplate.getForEntity(url, Example.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody());

        List<Example> all = exampleRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(findId);
        assertThat(all.get(0).getTitle()).isEqualTo(example.getTitle());
        assertThat(all.get(0).getContent()).isEqualTo(example.getContent());
    }
}
