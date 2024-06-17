package com.example.blog_back.service;

import com.example.blog_back.entity.User;
import com.example.blog_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public boolean isIdDuplicated(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean isNicknameDuplicated(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }

    public boolean isEmailDuplicated(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }
    
    public String loginUser(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        
        if (user == null) {
            return "User not found";
        } 
        if(!user.getUserPassword().equals(password)) {
            return "Invalid password";
        }
        return "Success";
    }
    
    public String getUserNickname(String userId) {
        User user = userRepository.findByUserId(userId);
        return user != null ? user.getUserNickname() : null;
    }
    
    public User findUserByNameAndEmail(String userName, String userEmail) {
        return userRepository.findByUserNameAndUserEmail(userName, userEmail);
    }
    
    public User findUserByIdAndEmail(String userId, String userEmail) {
        return userRepository.findByUserIdAndUserEmail(userId, userEmail);
    }
}
