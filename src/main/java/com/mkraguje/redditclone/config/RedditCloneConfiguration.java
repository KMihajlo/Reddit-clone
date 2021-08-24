package com.mkraguje.redditclone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("reddit-clone")
public class RedditCloneConfiguration {
    /**
     * Enter a stage of a project development
     */
    private String stage1 = "DEFAULT";
    private String stage2 = "DEFAULT";

    public String getStage1() {
        return stage1;
    }

    public void setStage1(String stage1) {
        this.stage1 = stage1;
    }

    public String getStage2() {
        return stage2;
    }

    public void setStage2(String stage2) {
        this.stage2 = stage2;
    }
}
