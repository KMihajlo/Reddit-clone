package com.mkraguje.redditclone.service;

import com.mkraguje.redditclone.model.Comment;
import com.mkraguje.redditclone.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }
}
