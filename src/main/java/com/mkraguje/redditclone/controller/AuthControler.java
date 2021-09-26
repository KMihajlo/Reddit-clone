package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthControler {

    private UserService userService;

    public AuthControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "auth/profile";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("success", false);
        return "auth/register";
    }
}
