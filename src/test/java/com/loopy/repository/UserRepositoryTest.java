package com.loopy.repository;

import com.loopy.ApplicationTests;
import com.loopy.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


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

    @Test
    public void read() { 

        Optional<User> user = userRepository.findByAccount("TestUser01");

        //user가 존재한다면,
        user.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
            System.out.println("email : "+ selectUser.getEmail());

            selectUser.getOrderDetailList().stream().forEach(detail ->{
                System.out.println(detail.getItem());
            });
        });

    }

    public void update() {

        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });



    }

    //transactional를 사용하면 실행은 되지만 롤백이 되어 실제로 db에 적용이 되진 않음.
    @Transactional
    @Test
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        // 제거하고자하는 데이터가 존재해야한다.
        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        //삭제되었는지 확인
        Optional<User> deleteUser = userRepository.findById(1L);

        // 제거한 값은 반드시 존해하지 않아야한다.
        Assert.assertFalse(deleteUser.isPresent());


    }
}
