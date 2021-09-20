package com.loopy.controller;


import com.loopy.model.SearchParam;
import org.springframework.web.bind.annotation.*;

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
    //getter,setter ?를 이용해서 받는다.
    @GetMapping("getMultiParam")
    public String multiParameter(SearchParam searchParam){
        //console에 출력.
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return"OK";
    }

}
