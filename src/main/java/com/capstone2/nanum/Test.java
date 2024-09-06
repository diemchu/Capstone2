package com.capstone2.nanum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/a")
    public String hello(){
        return  "hello";
    }
}
