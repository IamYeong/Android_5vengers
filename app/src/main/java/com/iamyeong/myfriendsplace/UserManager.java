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

        OnCompleteListener<DocumentSnapshot> onCompleteListener = new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                System.out.println("onComplete");

                if (task.isSuccessful()) {

                    String strId = toStringUserId(userId);
                    System.out.println(strId);

                    DocumentSnapshot doc = task.getResult();
                    System.out.println(doc + "아니..이게 왜 안 돼");

                    strUserName = (String) doc.getData().get(strId);
                    System.out.println(strUserName + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                }
            }
        };

        System.out.println(onCompleteListener);

        db.collection(userCollection)
                .document(userDocument)
                .get()
                .addOnCompleteListener(onCompleteListener);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@" + strUserName);
        return strUserName;
    }

    private String toStringUserId(long id) {

        String strId = Integer.toString((int) id);

        return strId;

    }


}
