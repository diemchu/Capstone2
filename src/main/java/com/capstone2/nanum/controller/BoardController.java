package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.Post;
import com.capstone2.nanum.services.PostService;
import com.capstone2.nanum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class BoardController {
    @Autowired
    PostService postService;

    @GetMapping("/board/write")
    public String showWritePage() {
        return "board/write";
    }

    @PostMapping("/board/save")
    public String savePost(
            @RequestParam String title,
            @RequestParam String content) {
        System.out.println(title);
        System.out.println(content);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(date);
        post.setNickname(UserService.user.getName());
        postService.savePost(post);
        return  "redirect:/home/board-view";

    }



}