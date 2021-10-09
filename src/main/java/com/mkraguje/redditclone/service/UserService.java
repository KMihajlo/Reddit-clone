package com.mkraguje.redditclone.service;

import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = new BCryptPasswordEncoder();
    }

    public User register(User user){
        // encode password
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);

        // confirm password
        user.setConfirmPassword(secret);
        // assign a role to the user
        user.addRole(roleService.findByName("ROLE_USER"));

        // set an activation code

        // disable the user

        // save the user
        save(user);

        // send the activation email
        sendActivationEmail(user);

        // return the user
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

    public void sendActivationEmail(User user){

    }
}
