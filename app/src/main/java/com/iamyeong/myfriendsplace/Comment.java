package com.iamyeong.myfriendsplace;

import java.util.Date;

public class Comment {

    private long userKakaoId;
    private String comment;
    private long times;

    public Comment() {}

    public Comment(long userId, String comment) {
        this.userKakaoId = userId;
        this.comment = comment;
        Date date = new Date();
        times = date.getTime();
    }

    public Comment(long userId, String comment, long time) {
        this.userKakaoId = userId;
        this.comment = comment;
        this.times = time;

    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public Long getUserKakaoId() {
        return userKakaoId;
    }

    public void setUserKakaoId(long userKakaoId) {
        this.userKakaoId = userKakaoId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
