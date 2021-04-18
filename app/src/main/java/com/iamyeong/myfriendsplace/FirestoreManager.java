package com.iamyeong.myfriendsplace;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirestoreManager {

    private FirebaseFirestore db;
    private Context context;
    private String storageKey;
    private CollectionReference collectionReference;
    private DocumentReference documentReference;

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

    public void getPosts() {

        //Get all document at this collection.
        db.collection(storageKey)
                //.whereEqualTo() <-- this configuration criteria
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for ( QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + ", " + document.getData());

                            }


                        }

                    }
                });




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
