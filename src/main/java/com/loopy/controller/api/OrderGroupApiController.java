package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.network.request.OrderGroupApiRequest;
import com.loopy.domain.network.response.OrderGroupApiResponse;
import com.loopy.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@RequestMapping("/api/orderGroup")
@RestController
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @PostConstruct
    public void init(){
        this.baseService = orderGroupApiLogicService;
    }
}
