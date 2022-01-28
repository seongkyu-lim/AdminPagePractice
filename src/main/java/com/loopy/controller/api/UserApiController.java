package com.loopy.controller.api;

import com.loopy.ifs.CrudInterface;
import com.loopy.model.network.Header;
import com.loopy.model.network.request.UserApiRequest;
import com.loopy.model.network.response.UserApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody UserApiRequest userApiRequest) {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody UserApiRequest userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
