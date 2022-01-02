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

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;


public class UserRepositoryTest extends ApplicationTests {

    //대표적인 디자인패턴으로 DI(Dependency Injection)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "seongkyu";
        String password = "limlim";
        String status = "user";
        String email = "afsdf@adf.com";
        String phoneNumber="010-2323-2323";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "admin";

        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
        Assert.assertEquals(newUser.getAccount(), account);
        Assert.assertEquals(newUser.getPassword(), password);
        Assert.assertEquals(newUser.getStatus(), status);
        Assert.assertEquals(newUser.getEmail(), email);
        Assert.assertEquals(newUser.getPhoneNumber(), phoneNumber);
        Assert.assertEquals(newUser.getRegisteredAt(), registeredAt);
        Assert.assertEquals(newUser.getCreatedAt(), createdAt);
        Assert.assertEquals(newUser.getCreatedBy(), createdBy);

    }

    @Test
    public void read() {

        String email = "afsdf@adf.com";

        Optional<User> optionalUser = userRepository.findByEmail(email);

        optionalUser.ifPresent(c ->{
            System.out.println(c.getAccount());
            System.out.println(c.getEmail());
            System.out.println(c.getPassword());
            System.out.println(c.getPhoneNumber());
        });
    }

    public void update() {

        // User user = userRepository.findFirstByPhoneNumberOrderByDesc("010-2323-2323");

        // Assert.assertNotNull(user);
        /**
        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
         **/



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
