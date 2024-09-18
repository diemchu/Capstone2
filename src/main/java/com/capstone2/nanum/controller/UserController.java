package com.capstone2.nanum.controller;


import com.capstone2.nanum.database.User;
import com.capstone2.nanum.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("login")
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
       // return "home/home.html";
        return "login/login.html";
    }

    //로그인 화면 요청
    @GetMapping("/login-view")
    public String loginView(){
        return  "login/login";
    }

    //로그인 처리
    @RequestMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {

        String message = null;
        User user = userService.findByEmail(email);
        if (user == null) {
            message = "login 정보를 틀렸습니다";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/login/login-view";
        } else {
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/home/home";
        }
    }
}
