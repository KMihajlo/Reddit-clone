package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.model.Vote;
import com.mkraguje.redditclone.repository.LinkRepository;
import com.mkraguje.redditclone.repository.UserRepository;
import com.mkraguje.redditclone.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteRepository voteRepository;

    private LinkRepository linkRepository;

    private UserRepository userRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    // http://lovalhost:8080/vote/link/1/direction/-1/voteCount/5
    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount, @AuthenticationPrincipal User user){
        Optional<Link> optionalLink = linkRepository.findById(linkID);
        if( optionalLink.isPresent() ) {
            Link link = optionalLink.get();
            boolean userVoted = false;
            int temp = 0;

            for(int i = 0; i < link.getVotes().size(); i++){
                if(link.getVotes().get(i).getUser().getId().equals(user.getId())){
                    userVoted = true;
                    temp = i;
                }
            }
            if(!userVoted){
                Vote vote = new Vote(direction, link, user);
                voteRepository.save(vote);

                int updatedVoteCount = voteCount + direction;
                link.setVoteCount(updatedVoteCount);
                link.addVote(vote);
                linkRepository.save(link);
                return updatedVoteCount;
            }else {
                if(link.getVotes().get(temp).getDirection() == 1 && direction == 1){
                    return voteCount;
                }else if(link.getVotes().get(temp).getDirection() == -1 && direction == -1){
                    return voteCount;
                }else if(link.getVotes().get(temp).getDirection() == 1 && direction == -1){
                    int updatedVoteCount = voteCount + direction;
                    link.setVoteCount(updatedVoteCount);

                    voteRepository.deleteById(link.getVotes().get(temp).getId());
                    link.getVotes().remove(temp);
                    Vote vote = new Vote(direction, link, user);
                    voteRepository.save(vote);
                    link.addVote(vote);
                    linkRepository.save(link);

                    return updatedVoteCount;
                }else if(link.getVotes().get(temp).getDirection() == -1 && direction == 1){
                    int updatedVoteCount = voteCount + direction;
                    link.setVoteCount(updatedVoteCount);

                    voteRepository.deleteById(link.getVotes().get(temp).getId());
                    link.getVotes().remove(temp);
                    Vote vote = new Vote(direction, link, user);
                    voteRepository.save(vote);
                    link.addVote(vote);
                    linkRepository.save(link);

                    return updatedVoteCount;
                }
            }
        }
        return voteCount;
    }
}
