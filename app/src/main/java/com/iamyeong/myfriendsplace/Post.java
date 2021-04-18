package com.iamyeong.myfriendsplace;

public class Post {

    private String publisher;
    private String title;
    //Timestamp
    private String content;


    public Post(String publisher, String title, String content) {

        this.publisher = publisher;
        this.title = title;
        this.content = content;
        //this.time = time;

    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
