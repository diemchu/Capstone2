package com.capstone2.nanum.repo;

import com.capstone2.nanum.database.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

