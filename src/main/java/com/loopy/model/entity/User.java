package com.loopy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity // == table.
//클래스명과 테이블명을 같게해주면 자동으로 매칭해줌.
public class User {

    @Id
    //어떤식으로 키전략을 가져갈 것인지 설정.( identity, auto 등 네가지 존재.)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;
    private String email;
    // DB에는 phone_number와 같이 snake 형식으로 표기하지만 자바에서는 camel case로 선언한다. : jpa가 ? 자동으로 매칭해줌.
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}