package com.loopy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;
    private String password;
    private String status;
    private String role;
    private LocalDateTime lastLoginAt;
    private LocalDateTime passwordUpdatedAt;
    private Integer loginFailCount;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    //값을 넣어주지 않아도  자동으로 생성, 수정해줍니다.(id 처럼)

    @CreatedDate
    private LocalDateTime createdAt;

    //component/LoginUserAuditorAware의 adnin을 받게됨.
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    //component/LoginUserAuditorAware의 adnin을 받게됨.
    @LastModifiedBy
    private String updatedBy;

}
