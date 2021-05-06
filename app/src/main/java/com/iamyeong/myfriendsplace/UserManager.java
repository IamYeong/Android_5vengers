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

    private static UserManager instance = new UserManager();

    private final String userCollection = "USERS";
    private final String userDocument = "USERSDOCUMENT";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Map<Long, String> idKeyMap = new HashMap<>();

    private UserManager() {}

    public static UserManager getInstance() {

        if (instance != null) {
            instance = new UserManager();
        }

        return instance;
    }

    public void setUser(long userId, String knickName) {

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("USERID", userId);
        userMap.put("USERNAME", knickName);


        db.collection(userCollection)
                .add(userMap);

    }

    public String toUserName(long userId) {

        String name = idKeyMap.get(userId);

        return name;
    }

    public void loadUserName(OnGetUserNameListener listener) {

        db.collection(userCollection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                long id = (Long) document.getData().get("USERID");
                                String name = (String) document.getData().get("USERNAME");

                                idKeyMap.put(id, name);
                                System.out.println(idKeyMap.size());
                                System.out.println(id + ", " + name);

                            }

                        }


                        listener.onGetUserName();
                    }
                });


    };

    private String toStringUserId(long id) {

        String strId = Integer.toString((int) id);

        return strId;

    }


}
