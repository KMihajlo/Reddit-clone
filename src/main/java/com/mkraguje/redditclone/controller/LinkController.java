package com.mkraguje.redditclone.controller;

import com.mkraguje.redditclone.model.Comment;
import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.service.LinkService;
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
import java.util.Optional;

@Controller
public class LinkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String list(Model model){
        LOGGER.info("'/' Route invoked...");
        model.addAttribute("links", linkService.findAll());
        return "link/list";
    }

    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id, Model model){
        LOGGER.info("'/link/{id}' Route invoked...");
        Optional<Link> link = linkService.findById(id);
        if(link.isPresent()){
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);
            model.addAttribute("comment", comment);
            model.addAttribute("link", currentLink);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model){
        model.addAttribute("link", new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(@Valid Link link,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal User user){
        if(bindingResult.hasErrors()){
            LOGGER.info("Validation errors were found while submitting a new link");
            model.addAttribute("link", link);
            return "/link/submit";
        }else{
            // save the link
            link.setUser(user);
            linkService.save(link);
            LOGGER.info("New link was saved successfully");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true)
                    .addFlashAttribute("isempty", false);
            return "redirect:/link/{id}";
        }
    }
}
