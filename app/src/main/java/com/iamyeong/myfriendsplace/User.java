package com.iamyeong.myfriendsplace;

public class User {

    private String knickName;
    private String profileImageURL;
    private String thumbnailURL;


    public User(String knicName, String profileImageURL, String thumbnailURL) {
        this.knickName = knicName;
        this.profileImageURL = profileImageURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getKnicName() {
        return knickName;
    }

    public void setKnicName(String knicName) {
        this.knickName = knicName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
