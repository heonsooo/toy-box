package com.fastcampus.ch1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String main(String[] args) {
        System.out.println("hi~");
        return "hello";
    }
}
