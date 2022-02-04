package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.entity.User;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.UserApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @GetMapping("/search")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort="id", size=10, direction = Sort.Direction.ASC) Pageable pageable){
        return baseService.search(pageable);
    }
}
