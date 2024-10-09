package com.capstone2.nanum.services;

import com.capstone2.nanum.database.User;
import com.capstone2.nanum.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // email로 유저 찾기
    public User findByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user  = userOptional.get();
            return  user;
        } else {
            return  null;
        }
    }

    @Transactional
    public  void updatePassword (String email, String newPassword){
        userRepository.updatePasswordByEmail(email,newPassword);
    }
}
