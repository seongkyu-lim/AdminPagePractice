package com.loopy.service;

import com.loopy.ifs.CrudInterface;
import com.loopy.model.entity.User;
import com.loopy.model.network.Header;
import com.loopy.model.network.request.UserApiRequest;
import com.loopy.model.network.response.UserApiResponse;
import com.loopy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Autowired
    private UserRepository userRepository;

    // 1. request data 가져오기.
    // 2. user 생성
    // 3. 생성된 데이터 -> response로 리턴하기.
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

         User user = User.builder()
                 .account(userApiRequest.getAccount())
                 .password(userApiRequest.getPassword())
                 .status("REGISTERED")
                 .email(userApiRequest.getEmail())
                 .phoneNumber(userApiRequest.getPhoneNumber())
                 .registeredAt(LocalDateTime.now())
                 .build();

         User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        Optional<User> optional = userRepository.findById(id);

        return optional
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        Optional<User> optional = userRepository.findById(userApiRequest.getId()) ;

        return optional
                .map(user -> {

                    // @Data, @Accessors(chain = true) 어노테이션으로 builder와 같이 set을 사용가능. (수정 시 사용하기 용이)
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setEmail(userApiRequest.getEmail())
                            .setPhoneNumber(userApiRequest.getPhoneNumber());
                    return user;
        })
                .map(user -> userRepository.save(user))
                .map(updateUser -> response(updateUser))
                .orElseGet(() -> Header.ERROR("업데이트할 데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<User> optional = userRepository.findById(id);

        return optional
                .map(user -> {
                    userRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("삭제할 데이터 없음"));
    }


    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                 .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
