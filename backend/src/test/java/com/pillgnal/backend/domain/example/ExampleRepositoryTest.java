package com.pillgnal.backend.domain.example;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleRepositoryTest {
    @Autowired
    ExampleRepository exampleRepository;

    @After
    public void cleanup() {
        exampleRepository.deleteAll();
    }

    @Test
    public void SaveAndLoad() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        exampleRepository.save(Example.builder()
            .title(title)
            .content(content)
            .author("jojoldu@gmail.com")
            .build());

        // when
        List<Example> exampleList = exampleRepository.findAll();

        // then
        Example posts = exampleList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    /**
     * JPA Auditing Test
     *
     * @author Eomjaewoong
     */
    @Test
    public void registeBaseTimeEntity() {
        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        exampleRepository.save(Example.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Example> exampleList = exampleRepository.findAll();

        // then
        Example example = exampleList.get(0);

        System.out.println(">>>>>>>>>> createDate="+example.getCreatedData()
                +", modifiedDate="+example.getModifieddate());

        assertThat(example.getCreatedData()).isAfter(now);
        assertThat(example.getModifieddate()).isAfter(now);
    }
}
