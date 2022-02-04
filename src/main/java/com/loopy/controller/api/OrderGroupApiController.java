package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.entity.OrderGroup;
import com.loopy.domain.network.request.OrderGroupApiRequest;
import com.loopy.domain.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orderGroup")
@RestController
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
