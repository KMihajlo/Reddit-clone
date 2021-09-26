package com.mkraguje.redditclone.bootstrap;

import com.mkraguje.redditclone.model.Comment;
import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.model.Role;
import com.mkraguje.redditclone.model.User;
import com.mkraguje.redditclone.service.CommentService;
import com.mkraguje.redditclone.service.LinkService;
import com.mkraguje.redditclone.service.RoleService;
import com.mkraguje.redditclone.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private LinkService linkService;
    private CommentService commentService;
    private UserService userService;
    private RoleService roleService;

    private Map<String, User> users = new HashMap<>();

    public DatabaseLoader(LinkService linkService, CommentService commentService, UserService userService, RoleService roleService) {
        this.linkService = linkService;
        this.commentService = commentService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {

        // add users and roles
        addUsersAndRoles();

        // add links
        Map<String,String> links = new HashMap<>();
        links.put("Securing Spring Boot APIs and SPAs with OAuth 2.0","https://auth0.com/blog/securing-spring-boot-apis-and-spas-with-oauth2/?utm_source=reddit&utm_medium=sc&utm_campaign=springboot_spa_securing");
        links.put("Easy way to detect Device in Java Web Application using Spring Mobile - Source code to download from GitHub","https://www.opencodez.com/java/device-detection-using-spring-mobile.htm");
        links.put("Tutorial series about building microservices with SpringBoot (with Netflix OSS)","https://medium.com/@marcus.eisele/implementing-a-microservice-architecture-with-spring-boot-intro-cdb6ad16806c");
        links.put("Detailed steps to send encrypted email using Java / Spring Boot - Source code to download from GitHub","https://www.opencodez.com/java/send-encrypted-email-using-java.htm");
        links.put("Build a Secure Progressive Web App With Spring Boot and React","https://dzone.com/articles/build-a-secure-progressive-web-app-with-spring-boo");
        links.put("Building Your First Spring Boot Web Application - DZone Java","https://dzone.com/articles/building-your-first-spring-boot-web-application-ex");
        links.put("Building Microservices with Spring Boot Fat (Uber) Jar","https://jelastic.com/blog/building-microservices-with-spring-boot-fat-uber-jar/");
        links.put("Spring Cloud GCP 1.0 Released","https://cloud.google.com/blog/products/gcp/calling-java-developers-spring-cloud-gcp-1-0-is-now-generally-available");
        links.put("Simplest way to Upload and Download Files in Java with Spring Boot - Code to download from Github","https://www.opencodez.com/uncategorized/file-upload-and-download-in-java-spring-boot.htm");
        links.put("Add Social Login to Your Spring Boot 2.0 app","https://developer.okta.com/blog/2018/07/24/social-spring-boot");
        links.put("File download example using Spring REST Controller","https://www.jeejava.com/file-download-example-using-spring-rest-controller/");

        links.forEach((k,v) -> {
            User u1 = users.get("user@gmail.com");
            User u2 = users.get("super@gmail.com");
            Link link = new Link(k, v);

            if(k.startsWith("Build")){
                link.setUser(u1);
            }else {
                link.setUser(u2);
            }

            linkService.save(link);

            // adding comments
            Comment spring = new Comment("Thank you for this link related to Spring Boot. I love it, great post!", link, u1);
            Comment security = new Comment("I love that you are talking about Spring Security", link, u1);
            Comment pwa = new Comment("What is this Progressive Web App thing all about? PWAs sound really cool.", link, u2);
            Comment[] comments = {spring, security, pwa};
            for(Comment comment : comments){
                commentService.save(comment);
                link.addComment(comment);
            }
        });

        long linkCount = linkService.count();
        System.out.println("Number of links in the database: " + linkCount );
    }

    private void addUsersAndRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role userRole = new Role("ROLE_USER");
        roleService.save(userRole);
        Role adminRole = new Role("ROLE_ADMIN");
        roleService.save(adminRole);

        User user = new User("user@gmail.com",secret,true,"Joe","User","joedirt");
        user.addRole(userRole);
        userService.save(user);
        users.put("user@gmail.com",user);

        User admin = new User("admin@gmail.com",secret,true,"Joe","Admin","masteradmin");
        admin.setAlias("joeadmin");
        admin.addRole(adminRole);
        userService.save(admin);
        users.put("admin@gmail.com",admin);

        User master = new User("super@gmail.com",secret,true,"Super","User","superduper");
        master.addRoles(new HashSet<>(Arrays.asList(userRole,adminRole)));
        userService.save(master);
        users.put("super@gmail.com",master);
    }
}
