package com.iamyeong.myfriendsplace;

public class Comment {

    private Long userKakaoId;
    private String comment;
    //Time stamp

    public Comment(Long userId, String comment) {
        this.userKakaoId = userId;
        this.comment = comment;
    }

    public Long getUserKakaoId() {
        return userKakaoId;
    }

    public void setUserKakaoId(Long userKakaoId) {
        this.userKakaoId = userKakaoId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
