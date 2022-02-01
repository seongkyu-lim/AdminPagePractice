package com.loopy.domain.repository;

import com.loopy.ApplicationTests;
import com.loopy.domain.entity.User;
import com.loopy.domain.enumclass.UserStatus;
import com.loopy.domain.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

        /**
        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        **/

        //Builder annoataion을 통해 builder pattern 적용.
        /*
        장점 1. 원하는 값들만 간편하게 생성할 수 있다. 생성자를 이용하면 entity클래스에 만들어 주어야한다.
        장점 2. 코드가 .으로 이어져 간편하다.
         */
        User user = User.builder()
                .account(account)
                .password(password)
                .status(UserStatus.REGISTERED)
                .email(email)
                .phoneNumber(phoneNumber)
                .registeredAt(registeredAt)
                .build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
        Assert.assertEquals(newUser.getAccount(), account);
        Assert.assertEquals(newUser.getPassword(), password);
        Assert.assertEquals(newUser.getStatus(), status);
        Assert.assertEquals(newUser.getEmail(), email);
        Assert.assertEquals(newUser.getPhoneNumber(), phoneNumber);
        Assert.assertEquals(newUser.getRegisteredAt(), registeredAt);

    }

    @Test
    @Transactional
    public void read() {

        // User user = userRepository.findFirstByPhoneNumberOrderByDesc("010-2323-2323");
        User user = userRepository.findByPhoneNumber("010-2323-2323");

        Assert.assertNotNull(user);

        // 1에서 n인 테이블로 접근할 경우.
        // query문에서 forien key로 접근하는 것이 아니라 역으로도 접근할 수 있다..!
        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("--------------주문 묶음---------------");
            System.out.println("수령인 : "+orderGroup.getRevName());
            System.out.println("수령지 : "+orderGroup.getRevAddress());
            System.out.println("총금액 : "+orderGroup.getTotalPrice());
            System.out.println("총수량 : "+orderGroup.getTotalQuantity());

            System.out.println("--------------주문 상세---------------");
            orderGroup.getOrderDetailList().stream().forEach(orderDetail ->{
                //n에서 1인 테이블로 접근할 경우.
                System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                System.out.println("고객 센터 번호 : "+orderDetail.getItem().getPartner().getPartnerNumber());
                System.out.println("partner사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상태 : "+orderDetail.getStatus());
                System.out.println("도착예정일자 : "+orderDetail.getArrivalDate());



            });
        });

        /*
        optionalUser.ifPresent(c ->{
            System.out.println(c.getAccount());
            System.out.println(c.getEmail());
            System.out.println(c.getPassword());
            System.out.println(c.getPhoneNumber());
        });
        */
    }

    @Test
    public void update() {

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
