package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.Comment;
import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.repository.CommentRepository;
import com.mkraguje.redditclone.service.CommentService;
import com.mkraguje.redditclone.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);

    private LinkService linkService;
    private CommentService commentService;

    public CommentController(LinkService linkService, CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult, @AuthenticationPrincipal User user){
        if(bindingResult.hasErrors()){
            LOGGER.info("There was a problem adding a new comment.");
        }else {
            comment.setUser(user);
            commentService.save(comment);
            LOGGER.info("New comment was successfully saved.");
        }
        return "redirect:/link/" + comment.getLink().getId();
    }
}
