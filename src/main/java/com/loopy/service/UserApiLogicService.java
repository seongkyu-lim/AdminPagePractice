package com.loopy.service;

import com.loopy.ifs.CrudInterface;
import com.loopy.domain.entity.User;
import com.loopy.domain.enumclass.UserStatus;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.UserApiResponse;
import com.loopy.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    // 1. request data 가져오기.
    // 2. user 생성
    // 3. 생성된 데이터 -> response로 리턴하기.
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

         User user = User.builder()
                 .account(userApiRequest.getAccount())
                 .password(userApiRequest.getPassword())
                 .status(UserStatus.REGISTERED)
                 .email(userApiRequest.getEmail())
                 .phoneNumber(userApiRequest.getPhoneNumber())
                 .registeredAt(LocalDateTime.now())
                 .build();

         User newUser = baseRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        Optional<User> optional = baseRepository.findById(id);

        return optional
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        Optional<User> optional = baseRepository.findById(userApiRequest.getId()) ;

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
                .map(baseRepository::save)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("업데이트할 데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<User> optional = baseRepository.findById(id);

        return optional
                .map(user -> {
                    baseRepository.delete(user);
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
