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
            @RequestParam("gender") String gender,
            @RequestParam("confirm-password") String confirm_password,
            RedirectAttributes redirectAttributes

    ) {
        User checkUser = userService.findByEmail(email);
        if(checkUser == null){
            User newUser = new User();
            newUser.setName(name);
            newUser.setAge(age);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setGender(gender);
            userService.createUser(newUser);
            return "login/login.html";
        }else{
            redirectAttributes.addFlashAttribute("message" , "이메일 중복 합니다!");
            redirectAttributes.addFlashAttribute("name",name);
            redirectAttributes.addFlashAttribute("age",age);
            redirectAttributes.addFlashAttribute("password",password);
            redirectAttributes.addFlashAttribute("gender",gender);
            redirectAttributes.addFlashAttribute("confirm_password",confirm_password);
            return "redirect:/login/signup-view";
        }
    }

    //로그인 화면 요청
    @GetMapping("/login-view")
    public String loginView(){
        return  "login/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {

        String message =" ";
        User user = userService.findByEmail(email);

        if (user == null) {
            message = "이메일을 틀렸습니다. 다시 확인하시기 바랍니다";
            redirectAttributes.addFlashAttribute("email",email);
            redirectAttributes.addFlashAttribute("password",password);
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/login/login-view";
        } else {
            if(!user.getPassword().equals(password)){
                message = "비밀번호를 틀렸습니다";
                redirectAttributes.addFlashAttribute("email",email);
                redirectAttributes.addFlashAttribute("password",password);
                redirectAttributes.addFlashAttribute("message", message);
                return "redirect:/login/login-view";
            }else{
                redirectAttributes.addFlashAttribute("message", message);
                return "home/home";
            }
        }
    }

    //비밀번호 찾기 화면 요청
    @GetMapping("/forget-password-view")
    public String forgetPassword() {
        return "login/forget-password";
    }

}
