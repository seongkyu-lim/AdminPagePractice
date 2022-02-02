package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.UserApiResponse;
import com.loopy.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse> {

    private final UserApiLogicService userApiLogicService;

    @PostConstruct
    public void init(){
        this.baseService = userApiLogicService;
    }


}
