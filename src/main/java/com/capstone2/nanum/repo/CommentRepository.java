package com.capstone2.nanum.repo;

import com.capstone2.nanum.database.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPosterId(Long posterId);
}

