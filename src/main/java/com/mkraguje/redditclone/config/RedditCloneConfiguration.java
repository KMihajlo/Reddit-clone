package com.mkraguje.redditclone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("reddit-clone")
public class RedditCloneConfiguration {
    /**
     * Enter a stage of a project development
     */
    private String stage = "DEFAULT";

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

}
