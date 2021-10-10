package com.mkraguje.redditclone.service;

import com.mkraguje.redditclone.config.EmailConfiguration;
import com.mkraguje.redditclone.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;


@Service
public class MailService {

    @Autowired
    private EmailConfiguration emailConfiguration;

    private final SpringTemplateEngine templateEngine;
    private final String BASE_URL = "http://localhost:8080";

    private final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    public MailService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(emailConfiguration.getHost());
        mailSenderImpl.setPort(emailConfiguration.getPort());
        mailSenderImpl.setUsername(emailConfiguration.getUsername());
        mailSenderImpl.setPassword(emailConfiguration.getPassword());
        return mailSenderImpl;
    }

    @Async
    public void sendEmail(String to,
                          String subject,
                          String content,
                          boolean isMultiPart,
                          boolean isHtml) {
        LOGGER.debug("Sending Email");

        MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom("noreply@redditclone.com");
            message.setSubject(subject);
            message.setText(content,isHtml);
            getJavaMailSender().send(mimeMessage);
        } catch (Exception e) {
            LOGGER.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String subject) {
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseURL", BASE_URL);
        String content = templateEngine.process(templateName,context);
        sendEmail(user.getEmail(),subject,content,false,true);
    }

    @Async
    public void sendActivationEmail(User user) {
        LOGGER.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/activation", "Reddit Clone User Activation");
    }

    @Async
    public void sendWelcomeEmail(User user) {
        LOGGER.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/welcome", "Welcome new Reddit Clone User!");
    }

//    public void sendMessage(String to, String from, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        message.setFrom(from);
//        getJavaMailSender().send(message);
//    }
}
