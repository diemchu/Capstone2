package com.capstone2.nanum.controller;

import com.capstone2.nanum.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendCode(@RequestParam String receiver, RedirectAttributes redirectAttributes) {
        String code = generateRandomCode();
        String subject = "나눔의 인증번호";
        String body = "인증본호: " + code ;
        emailService.sendEmail(receiver, subject, body);
        redirectAttributes.addFlashAttribute("message", code);
        redirectAttributes.addFlashAttribute("info", "Email sent successfully");
//        redirectAttributes.addFlashAttribute("message",code);
        System.out.println(code);
        return "redirect:/login/signup-view";
    }

    private String generateRandomCode() {
        return String.valueOf((int) (Math.random() * 1000000));
    }
}


