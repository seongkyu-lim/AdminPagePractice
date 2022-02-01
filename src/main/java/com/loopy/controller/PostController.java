package com.loopy.controller;

import com.loopy.domain.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // post가 사용되는 경우 !
    // HTML <Form>
    // ajax로 검색

    //Post 방식으로 클라이언트로부터 데이터 받기.
    @PostMapping("/postMethod") // @RequestMapping도 가능합니다.
    //POST mapping은 requestBody를 사용해주는 이유는 ?
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    //@PutMapping

    //@PatchMapping

    //@DeleteMapping


}
