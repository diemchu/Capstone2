package com.capstone2.nanum.controller;

import com.capstone2.nanum.database.Post;
import com.capstone2.nanum.services.PostService;
import com.capstone2.nanum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("board")
public class BoardController {
    @Autowired
    PostService postService;

    @GetMapping("/write")
    public String showWritePage() {
        return "board/write";
    }

    @PostMapping("/save")
    public String savePost(
            @RequestParam String title,
            @RequestParam String content
    ) {
        System.out.println(title);
        System.out.println(content);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(date);
        post.setNickname(UserService.user.getName());
        postService.savePost(post);

        Long currentUserId = UserService.user.getId();
        return  "redirect:/home/board-view";



    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long roomId) {
        Map<String, Object> response = new HashMap<>();
        try {
            postService.deletePostById(roomId);
            response.put("success", true);
            response.put("message", "Post deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error deleting post");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }





}