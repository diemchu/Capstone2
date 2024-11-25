package com.capstone2.nanum.controller;


import com.capstone2.nanum.database.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private com.capstone2.nanum.repo.CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment newComment) {
        try {
            commentRepository.save(newComment);
            return ResponseEntity.ok().body("Comment added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while adding comment");
        }
    }
}

