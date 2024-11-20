package com.capstone2.nanum.repo;


import com.capstone2.nanum.database.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    void deleteById(Long id);
}

