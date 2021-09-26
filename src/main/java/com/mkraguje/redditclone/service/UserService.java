package com.mkraguje.redditclone.service;

import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user){
        return user;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void saveUsers(User... users){
        for(User user : users){
            LOGGER.info("Saving user: " + user.getEmail());
            userRepository.save(user);
        }
    }
}
