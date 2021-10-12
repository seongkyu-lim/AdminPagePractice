package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends ApplicationTests {

    //대표적인 디자인패턴으로 DI(Dependency Injection)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {

        User user = new User();


        user.setAccount("TestUser01");
        user.setEmail("TestUser01@naver.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+ newUser);
    }

    public void read() {
    }

    public void update() {

    }

    public void delete() {

    }
}
