package com.iamyeong.myfriendsplace;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirestoreManager {

    private FirebaseFirestore db;

    public FirestoreManager() {

        db = FirebaseFirestore.getInstance();
    }

    public void addPost(String collectionName, String postTitle, String postContent) {

        Map<String, Object> map = new HashMap<>();
        map.put(postTitle, postContent);
        db.collection(collectionName).document().set(map);

    }

    public void editPost() {

    }

    public void getPost() {

    }

    public void deletePost() {

    }


    public void addComment() {

    }

    public void editComment() {

    }

    public void getComments() {

    }

    public void deleteComment() {

    }



}
