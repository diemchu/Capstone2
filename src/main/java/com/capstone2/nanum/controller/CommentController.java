package com.capstone2.nanum.controller;


import com.capstone2.nanum.database.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private com.capstone2.nanum.repo.CommentRepository commentRepository;

    @PostMapping("/add_comment")
    public ResponseEntity<?> addComment(@RequestBody Comment newComment) {
        try {
            commentRepository.save(newComment);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Comment added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error occurred while adding comment");
            return ResponseEntity.status(500).body(response);
        }
    }
    @GetMapping("/get_comments/{posterId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long posterId) {
        try {
            List<Comment> comments = commentRepository.findByPosterId(posterId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}

