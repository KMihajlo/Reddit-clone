package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.service.LinkService;
import com.mkraguje.redditclone.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;
    private LinkService linkService;

    public AuthController(UserService userService, LinkService linkService) {
        this.userService = userService;
        this.linkService = linkService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user){
        LOGGER.info("'/' Route invoked...");
        List<Link> links = linkService.findByUser(user);
        model.addAttribute("links", links);
        if(links.size() == 0){
            model.addAttribute("isempty", true);
        }else{
            model.addAttribute("isempty", false);
        }
        model.addAttribute("user", user);
        return "auth/profile";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            // show validation errors
            LOGGER.info("Validation errors were found while registering a new user");
            model.addAttribute("user",user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "auth/register";
        } else {
            // Register new user
            User newUser = userService.register(user);
            redirectAttributes
                    .addAttribute("id",newUser.getId())
                    .addFlashAttribute("success",true);
            LOGGER.info("Successfully registered a new user!");
            return "redirect:/register";
        }
    }

    @GetMapping("/activate/{email}/{activationCode}")
    public String activate(@PathVariable String email, @PathVariable String activationCode){
        Optional<User> user = userService.findByEmailAndActivationCode(email, activationCode);
        if(user.isPresent()){
            User newUser = user.get();
            newUser.setEnabled(true);
            newUser.setConfirmPassword(newUser.getPassword());
            userService.save(newUser);
            userService.sendWelcomeEmail(newUser);
            return "auth/activated";
        }
        return "redirect:/";
    }
}
