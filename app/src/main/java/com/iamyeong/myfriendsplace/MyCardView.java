package com.iamyeong.myfriendsplace;

public class MyCardView {

    private String title;
    private String user;
    //private int commentCount;
    //private boolean newest;


    public MyCardView(String title, String user) {

        this.title = title;
        this.user = user;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
