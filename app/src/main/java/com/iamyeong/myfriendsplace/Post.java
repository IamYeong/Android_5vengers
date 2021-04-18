package com.iamyeong.myfriendsplace;

public class Post {

    private Long publisherId;
    private String title;
    //Timestamp
    private String content;


    public Post(Long publisher, String title, String content) {

        this.publisherId = publisher;
        this.title = title;
        this.content = content;
        //this.time = time;

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
