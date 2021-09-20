package com.loopy.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path ="/getMethod") // /api/getMethod
    public String getRequest(){

        return "Hi getMethod";

    }
    ////client로부터 변수 받아오는 방법.
    @GetMapping("/getParameter") //localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password){
             System.out.println("id : "+ id);
             System.out.println("password : "+password);

             return id+password;
    }
    //많은 개수의 변수를 한번에 받는 방법.
    public String multiParameter()

}
