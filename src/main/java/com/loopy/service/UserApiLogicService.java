package com.loopy.service;

import com.loopy.domain.entity.Item;
import com.loopy.domain.entity.OrderDetail;
import com.loopy.domain.entity.OrderGroup;
import com.loopy.domain.entity.User;
import com.loopy.domain.enumclass.UserStatus;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.Pagination;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.ItemApiResponse;
import com.loopy.domain.network.response.OrderGroupApiResponse;
import com.loopy.domain.network.response.UserApiResponse;
import com.loopy.domain.network.response.UserOrderInfoApiResponse;
import com.loopy.domain.repository.UserRepository;
import com.loopy.ifs.CrudInterface;
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
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    private final UserRepository userRepository;
    private final OrderGroupApiLogicService orderGroupApiLogicService;
    private final ItemApiLogicService itemApiLogicService;

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

         User newUser = userRepository.save(user);

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        Optional<User> optional = userRepository.findById(id);

        return optional
                .map(user -> Header.OK(response(user)))
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
                .map(userRepository::save)
                .map(user -> Header.OK(response(user)))
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


    public Header<List<UserApiResponse>> search(Pageable pageable){

        Page<User> users = userRepository.findAll(pageable);

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

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        //user

        User user = userRepository.getOne(id);

        UserApiResponse userApiResponse = response(user);



        //orderGroup

        // user 데이터를 만들때, orderGroupList에 대한 값을 생성해주지 않았는데, 그러면 빈값인거 아닌가 ?
        List<OrderGroup> orderGroupList = user.getOrderGroupList();

        List<OrderGroupApiResponse> orderGroupApiResponseList =  orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse =  orderGroupApiLogicService.response(orderGroup).getData();

                    //ItemApiResponse
                    List<ItemApiResponse> itemApiResponseList =  orderGroup.getOrderDetailList().stream()
                            .map(orderDeTail -> {
                                Item item = orderDeTail.getItem();
                                return itemApiLogicService.response(item).getData();
                            })
                            .collect(Collectors.toList());

                    return orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);

    }
}
