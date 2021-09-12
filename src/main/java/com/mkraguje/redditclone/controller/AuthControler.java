package com.mkraguje.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthControler {

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
}
