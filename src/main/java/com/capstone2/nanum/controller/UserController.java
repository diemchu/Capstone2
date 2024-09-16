package com.capstone2.nanum.controller;


import com.capstone2.nanum.database.User;
import com.capstone2.nanum.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원가입 화면
    @GetMapping("/signup-view")
    public String signupView() {
        return "login/sign-up.html";
    }
    //회원가입 요청
    @PostMapping("/create")
    public String create(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("nickname") String nickname,
            @RequestParam("gender") String gender
    ) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setEmail(email);
        newUser.setNickname(nickname);
        newUser.setPassword(password);
        newUser.setGender(gender);
        userService.createUser(newUser);
        return "home/home.html";
    }
}
