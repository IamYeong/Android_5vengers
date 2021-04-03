package com.iamyeong.myfriendsplace;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PostListGetter {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    private CollectionReference collectionReference;


    public PostListGetter(String collectionName) {

        collectionReference = db.collection(collectionName);

    }

    public void setDocument(String documentName) {

        if (collectionReference == null) {
            Log.e("PostListGetter : ", "collection reference is null");
            return;
        }

        documentReference = collectionReference.document(documentName);

    }

    public void getAllPost() {

    }

    public void getPostAtDocument(String documentName) {
 
        if (db == null) {
            Log.e("Collection is :", "null");
            return;
        }

        db.document(documentName);

    }



}
