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

    public void addPost(Post post) {

        //Auto document id construct
        db.collection(storageKey)
                .add(post);

    }

    public void editPost() {


    }


    public void deletePost() {

    }


    public void addComment(Comment comment) {

        db.collection(storageKey).document("1joGN0UYqdtMlWUjQNax")
                .collection(storageKey)
                .add(comment);

    }

    public void editComment() {

    }

    public void getComments() {

    }

    public void deleteComment() {

    }



}
