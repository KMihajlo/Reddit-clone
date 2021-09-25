package com.mkraguje.redditclone.service;

import com.mkraguje.redditclone.model.Vote;
import com.mkraguje.redditclone.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote){
        return voteRepository.save(vote);
    }

    public void deleteById(Long id){
        voteRepository.deleteById(id);
    }
}
