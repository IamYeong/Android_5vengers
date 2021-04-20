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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreManager {

    private static FirestoreManager firestoreManager;
    private static FirebaseFirestore db;
    private static String storageKey;
    private CollectionReference collectionReference;
    private DocumentReference documentReference;
    private List<Post> postList = new ArrayList<>();

    private FirestoreManager() {}

    public static FirestoreManager getInstance(Context context) {

        if (firestoreManager == null) {
            firestoreManager = new FirestoreManager();

            db = FirebaseFirestore.getInstance();
            storageKey = context.getString(R.string.collection_name);

        }

        return firestoreManager;

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

                            postList.clear();
                            for ( QueryDocumentSnapshot document : task.getResult()) {


                                String title = (String) document.getData().get("title");
                                String content = (String) document.getData().get("content");
                                long publisherId = (Long) document.getData().get("publisher");
                                long times = (Long) document.getData().get("times");
                                String documentId = document.getId();

                                System.out.println(documentId + ", " + publisherId + ", " + title + ", " + content + ", " + times);

                                Post post = new Post(documentId, publisherId, title, content, times);



                                postList.add(post);

                            }

                            System.out.println(postList.size());


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
