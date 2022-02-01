package com.loopy.service;

import com.loopy.ifs.CrudInterface;
import com.loopy.domain.entity.OrderGroup;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.request.OrderGroupApiRequest;
import com.loopy.domain.network.response.OrderGroupApiResponse;
import com.loopy.domain.repository.OrderGroupRepository;
import com.loopy.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupRepository orderGroupRepository;
    private final UserRepository userRepository;


    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(orderGroupApiRequest.getStatus())
                .orderType(orderGroupApiRequest.getOrderType())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .paymentType(orderGroupApiRequest.getPaymentType())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                // user객체와 연결해주면서 user에도 연결을 해주어야되는 것아닌가? -> 실제 db에서는 적용안되는 것이니 나중에 해도 상관없나?
                .user(userRepository.getOne(orderGroupApiRequest.getUserId()))
                .build();

        OrderGroup newOrderGroup =  orderGroupRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음."));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        Optional<OrderGroup> optional = orderGroupRepository.findById(orderGroupApiRequest.getId());


        return optional.map(orderGroup -> {

            orderGroup.setStatus(orderGroupApiRequest.getStatus())
                    .setOrderType(orderGroupApiRequest.getOrderType())
                    .setRevAddress(orderGroupApiRequest.getRevAddress())
                    .setRevName(orderGroupApiRequest.getRevName())
                    .setPaymentType(orderGroupApiRequest.getPaymentType())
                    .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                    .setTotalQuantity(orderGroupApiRequest.getTotalQuantity())
                    .setOrderAt(orderGroupApiRequest.getOrderAt())
                    .setArrivalDate(orderGroupApiRequest.getArrivalDate())
                    .setUser(userRepository.getOne(orderGroupApiRequest.getUserId()))
                    .builder();

             OrderGroup newOrderGroup =  orderGroupRepository.save(orderGroup); // -> save하면 기존의 값이 업데이트 되는 것이 맞나..?
            return response(newOrderGroup);

        }).orElseGet(()->Header.ERROR("데이터 없음."));
    }

    @Override
    public Header delete(Long id) {

        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터없음."));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        // user -> userApiResponse
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(orderGroupApiResponse);
    }
}
