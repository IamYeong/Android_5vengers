package com.iamyeong.myfriendsplace;

import android.util.Log;

public class UserModel {

    private static UserModel instance = new UserModel();

    private String knickName;
    private String imageURL;
    private String thumbnailURL;
    private String email;

    private UserModel() {}

    public static UserModel getInstance() {

        if (instance == null) {
            Log.e("UserModel", "is Null");

        }

        return instance;
    }


    public String getKnickName() {
        return knickName;
    }

    public void setKnickName(String knickName) {
        this.knickName = knickName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
