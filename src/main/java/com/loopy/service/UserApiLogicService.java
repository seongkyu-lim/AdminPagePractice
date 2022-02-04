package com.loopy.service;

import com.loopy.domain.entity.User;
import com.loopy.domain.enumclass.UserStatus;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.Pagination;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        Optional<User> optional = baseRepository.findById(id);

        return optional
                .map(user -> Header.OK(response(user)))
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
                .map(user -> Header.OK(response(user)))
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


    public Header<List<UserApiResponse>> search(Pageable pageable){

        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(this::response)
                .collect(Collectors.toList());

        //pageable로 찾으면 아래와 같은 정보도 얻을 수 있음.
        Pagination pagination = Pagination.builder()
                .totalElements(users.getTotalElements())
                .totalPages(users.getTotalPages())
                .currentElements(users.getNumberOfElements())
                .currentPage(users.getNumber())
                .build();

        return Header.OK(userApiResponseList, pagination);
    }


    private UserApiResponse response(User user){
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

        return userApiResponse;
    }
}
