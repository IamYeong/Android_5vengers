package com.iamyeong.myfriendsplace;

import android.util.Log;

public class UserModel {

    //This class can be Builder pattern.
    private Long userId;
    private String userName;
    private String imageURL;
    private String thumbnailURL;


    private static UserModel instance = new UserModel();

    private UserModel() {}

    public static UserModel getInstance() {

        if (instance == null) {
            Log.e("UserModel is", "null");
        }

        return instance;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
