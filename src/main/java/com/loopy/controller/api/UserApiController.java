package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.entity.User;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.UserApiResponse;
import com.loopy.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {
}
