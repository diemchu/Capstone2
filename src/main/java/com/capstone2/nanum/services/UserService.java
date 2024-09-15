package com.capstone2.nanum.services;

import com.capstone2.nanum.database.User;
import com.capstone2.nanum.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
