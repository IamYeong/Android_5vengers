package com.iamyeong.myfriendsplace;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirestoreManager {

    private FirebaseFirestore db;
    private Context context;
    private String storageKey;

    public FirestoreManager(Context activityContext) {

        this.context = activityContext;
        db = FirebaseFirestore.getInstance();
        storageKey = context.getString(R.string.collection_name);

    }

    public void addPost(String publisher, String postTitle, String postContent) {

        //Auto document id construct
        db.collection(storageKey)
                .add(new Post(publisher, postTitle, postContent));

    }

    public void editPost() {


    }


    public void deletePost() {

    }


    public void addComment(String document, String user, String comment) {

        db.collection(storageKey).document(document)
                .collection(document)
                .add(new Comment(user, comment));

    }

    public void editComment() {

    }

    public void getComments() {

    }

    public void deleteComment() {

    }



}
