package com.loopy.controller;


import com.loopy.model.SearchParam;
import org.springframework.web.bind.annotation.*;

//문자열 반환
@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path ="/getMethod") // /api/getMethod
    public String getRequest(){

        return "Hi getMethod";

    }
    ////client로부터 변수 받아오는 방법.
    @GetMapping("/getParam") //localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password){
             System.out.println("id : "+ id);
             System.out.println("password : "+password);

             return id+password;
    }
    //많은 개수의 변수를 한번에 받는 방법.
    //getter,setter(생성자?)를 이용해서 받는다.
    @GetMapping("/getMultiParam")
    //객체를 리턴하게되면 스프링부트가 jackson이라는 내장 라이브러리 이용하여 json 형태로 자동으로 반환.
    public SearchParam multiParameter(SearchParam searchParam){
        //console에 출력.
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
    }
}

