package com.loopy;


//메인 클래스
//스프링부트 자동 설정, BEAN 읽기 및 설정 자동화

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
