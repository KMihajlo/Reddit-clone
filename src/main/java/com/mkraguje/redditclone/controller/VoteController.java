package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.model.Vote;
import com.mkraguje.redditclone.service.LinkService;
import com.mkraguje.redditclone.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteService voteService;

    private LinkService linkService;

    public VoteController(VoteService voteService, LinkService linkService) {
        this.voteService = voteService;
        this.linkService = linkService;
    }

    // http://lovalhost:8080/vote/link/1/direction/-1/voteCount/5
    // @CrossOrigin(origins = "http://localhost:8080")
    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount, @AuthenticationPrincipal User user){
        Optional<Link> optionalLink = linkService.findById(linkID);
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
                voteService.save(vote);

                int updatedVoteCount = voteCount + direction;
                link.setVoteCount(updatedVoteCount);
                link.addVote(vote);
                linkService.save(link);
                return updatedVoteCount;
            }else {
                if(link.getVotes().get(temp).getDirection() == 1 && direction == 1){
                    return voteCount;
                }else if(link.getVotes().get(temp).getDirection() == -1 && direction == -1){
                    return voteCount;
                }else if(link.getVotes().get(temp).getDirection() == 1 && direction == -1){
                    int updatedVoteCount = voteCount + direction;
                    link.setVoteCount(updatedVoteCount);

                    voteService.deleteById(link.getVotes().get(temp).getId());
                    link.getVotes().remove(temp);
                    Vote vote = new Vote(direction, link, user);
                    voteService.save(vote);
                    link.addVote(vote);
                    linkService.save(link);

                    return updatedVoteCount;
                }else if(link.getVotes().get(temp).getDirection() == -1 && direction == 1){
                    int updatedVoteCount = voteCount + direction;
                    link.setVoteCount(updatedVoteCount);

                    voteService.deleteById(link.getVotes().get(temp).getId());
                    link.getVotes().remove(temp);
                    Vote vote = new Vote(direction, link, user);
                    voteService.save(vote);
                    link.addVote(vote);
                    linkService.save(link);

                    return updatedVoteCount;
                }
            }
        }
        return voteCount;
    }
}
