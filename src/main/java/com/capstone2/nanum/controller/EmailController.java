package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.User;
import com.capstone2.nanum.services.EmailService;
import com.capstone2.nanum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("email")
public class EmailController {
    public static  String code = null;
    public static  String email = null;
    @Autowired
    private EmailService emailService;
    private final UserService userService;

    public EmailController(UserService userService) {
        this.userService = userService;
    }



    @ResponseBody
    @PostMapping("/send-email")
    public Map<String, String> sendCode(@RequestParam String email, RedirectAttributes redirectAttributes) {
        Map<String, String> response = new HashMap<>();
        EmailController.email = email;
        EmailController.code = generateRandomCode();
        String subject = "나눔의 인증번호";
        String body = "인증본호: " + code;
        System.out.println(email + "aaaaa");
        try {
            emailService.sendEmail(email, subject, body);
            response.put("message", "인증코드 전송했습니다");
            response.put("status", "success");
        } catch (Exception e) {
            response.put("message", "이메일 전송 실패했습니다");
            response.put("status", "error");
        }
        return response;
    }

    //비밀번호 변경 페이지 기능
    @PostMapping("/change-password")
    @ResponseBody
    public Map<String, String> changePassword(@RequestParam String email) {

        Map<String, String> response = new HashMap<>();
        User user = userService.findByEmail(email);
        if (user == null) {
            response.put("message", "존재하지 않는 이메일 입니다");
            return response;
        }

        EmailController.email = user.getEmail();
        code = generateRandomCode();
        String subject = "나눔의 인증번호";
        String body = "인증본호: " + code;
        System.out.println(email + "aaaaa");
        try {
            emailService.sendEmail(email, subject, body);
            response.put("message", "인증코드 전송했습니다");
            response.put("status", "success");
        } catch (Exception e) {
            response.put("message", "이메일 전송 실패했습니다");
            response.put("status", "error");
        }

        return response;
    }

    private String generateRandomCode() {
        return String.valueOf((int) (Math.random() * 1000000));
    }
}


