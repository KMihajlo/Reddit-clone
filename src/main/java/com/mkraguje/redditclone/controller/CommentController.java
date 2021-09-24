package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.Comment;
import com.mkraguje.redditclone.repository.CommentRepository;
import com.mkraguje.redditclone.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);

    private LinkRepository linkRepository;
    private CommentRepository commentRepository;

    public CommentController(LinkRepository linkRepository, CommentRepository commentRepository) {
        this.linkRepository = linkRepository;
        this.commentRepository = commentRepository;
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            LOGGER.info("There was a problem adding a new comment.");
        }else {
            commentRepository.save(comment);
            LOGGER.info("New comment was successfully saved.");
        }
        return "redirect:/link/" + comment.getLink().getId();
    }
}
