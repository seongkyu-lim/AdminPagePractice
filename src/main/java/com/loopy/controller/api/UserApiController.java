package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.entity.User;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.request.UserApiRequest;
import com.loopy.domain.network.response.UserApiResponse;
import com.loopy.domain.network.response.UserOrderInfoApiResponse;
import com.loopy.ifs.CrudInterface;
import com.loopy.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("/order_info/{id}")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){

        return userApiLogicService.orderInfo(id);
    }

    @GetMapping("/search")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort="id", size=10, direction = Sort.Direction.ASC) Pageable pageable){
        return userApiLogicService.search(pageable);
    }

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.create(request);
    }

    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<UserApiResponse> delete(@PathVariable Long id) {
        return userApiLogicService.delete(id);
    }
}
