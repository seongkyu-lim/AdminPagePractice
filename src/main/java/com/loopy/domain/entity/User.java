package com.loopy.domain.entity;

import com.loopy.domain.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
//모든 생성자.
@AllArgsConstructor
//기본 생성자.
@NoArgsConstructor
@Entity // == table.
//클래스명과 테이블명을 같게해주면 자동으로 매칭해줌.
@ToString(exclude = {"orderGroup"})
@EntityListeners(AuditingEntityListener.class)
//객체 생성 시 사용.
@Builder
//객체 수정 시 사용. (builder와 비슷하게 체인형식으로 코드 심플화 가능) with set of Data
@Accessors(chain = true)
public class User {

    @Id
    //어떤식으로 키전략을 가져갈 것인지 설정.( identity, auto 등 네가지 존재.)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String email;
    // DB에는 phone_number와 같이 snake 형식으로 표기하지만 자바에서는 camel case로 선언한다. : jpa가 ? 자동으로 매칭해줌.
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // user 1 : n ordergroup
    // n개이기에 당연히 List로 받아와야함.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

}
