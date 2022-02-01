package com.loopy.domain.repository;

import com.loopy.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// repository는 쿼리문을 작성하는 대신 연결된 table에 대하여 CRUD를 객체지향적인 코드로 구현합니다.

@Repository
// interface로 파일 생성 후 jpa에서 제공되는 jparepository를 확장해오면 됩니다. <> 안에 인자는 원하는 entity와 pk의 타입을 명시해 줍니다.
public interface UserRepository extends JpaRepository<User, Long> {

    // query method.
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    Optional<User> findByAccountAndEmail(String account, String email);

    User findByPhoneNumber(String phoneNumber);

    // User 객체를 바로 반환.
    // phonenumber가 중복되는 경우 findFirstBy를 적용하면 가장 최근의 것을 조회.
    // OrderByDesc로 가장 늦게 추가된 것을 조회.
    // User findFirstByPhoneNumberOrderByDesc(String phoneNumber);
}
