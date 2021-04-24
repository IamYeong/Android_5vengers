package com.iamyeong.myfriendsplace;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final String userCollection;
    private final String userDocument;
    private String strUserName;
    private FirebaseFirestore db;
    private CollectionReference collection;
    private DocumentReference document;

    public UserManager(Context activityContext) {

        userCollection = activityContext.getString(R.string.users);
        userDocument = activityContext.getString(R.string.users_document);
        db = FirebaseFirestore.getInstance();
    }

    public void setUser(long userId, String knickName) {

        String strId = toStringUserId(userId);

        Map<String, String> userMap = new HashMap<>();
        userMap.put(strId, knickName);

        db.collection(userCollection)
                .document(userDocument)
                .set(userMap, SetOptions.merge());

    }

    public String toUserName(long userId) {

        String strId = toStringUserId(userId);

        db.collection(userCollection)
                .document(userDocument)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {

                            DocumentSnapshot doc = task.getResult();
                            strUserName = (String) doc.getData().get(strId);

                        }

                    }
                });

        return strUserName;
    }

    private String toStringUserId(long id) {

        String strId = Integer.toString((int) id);

        return strId;

    }


}