package com.capstone2.nanum.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {
    @GetMapping("/home")
    public  String home(){
        return  "home/home";
    }
    @GetMapping("/board-view")
    public  String board(){
        return  "board/board";
    }

    @GetMapping("/user-info-view")
    public  String user_info(){
        return  "home/user-info";
    }


}
