package com.loopy.service;

import com.loopy.ifs.CrudInterface;
import com.loopy.model.network.Header;
import com.loopy.model.network.request.UserApiRequest;
import com.loopy.model.network.response.UserApiResponse;
import com.loopy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Autowired
    private UserRepository userRepository;

    // 1. request data 가져오기.
    // 2. user 생성
    // 3. 생성된 데이터 -> response로 리턴하기.
    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {
        return null;
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(UserApiRequest request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
