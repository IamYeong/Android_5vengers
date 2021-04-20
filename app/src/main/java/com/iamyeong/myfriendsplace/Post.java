package com.iamyeong.myfriendsplace;


import java.util.Date;

public class Post {

    private long publisherId;
    private String title;
    private long times;
    private String content;
    private String postId;

    public Post() {}

    //For post add
    public Post(Long publisher, String title, String content) {

        this.publisherId = publisher;
        this.title = title;
        this.content = content;
        Date date = new Date();
        times = date.getTime();

    }

    //For get post
    public Post(String postId, Long publisher, String title, String content, long time) {

        this.postId = postId;
        this.publisherId = publisher;
        this.title = title;
        this.content = content;
        this.times = time;

    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Long getPublisher() {
        return publisherId;
    }

    public void setPublisher(Long publisher) {
        this.publisherId = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
