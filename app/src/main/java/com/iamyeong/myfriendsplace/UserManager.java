package com.iamyeong.myfriendsplace;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
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

    private static final UserManager instance = new UserManager();

    private final String userCollection = "USERS";
    private final String userDocument = "USERSDOCUMENT";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Map<String, String> idKeyMap = new HashMap<>();

    private UserManager() {}

    public static UserManager getInstance() {

        return instance;
    }

    public void setUser(String userId, String knickName) {

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("USERNAME", knickName);

        db.collection(userCollection)
                .document(userId)
                .set(userMap);

    }

    public void checkUser(long id, String kickName) {

        String strId = toStringUserId(id);

        if (idKeyMap.containsKey(strId)) {
            //Already exist user case

            db.collection(userCollection)
                    .document(strId)
                    .delete();

            setUser(strId, kickName);
        }

        setUser(strId, kickName);
    }

    public String toUserName(long userId) {

        String strId = toStringUserId(userId);
        String name = idKeyMap.get(strId);

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

                                String name = (String) document.getData().get("USERNAME");

                                idKeyMap.put(document.getId(), name);

                                System.out.println(document.getId() + ", " + name);

                            }

                            System.out.println(idKeyMap.size());

                            listener.onGetUserName();

                        }

                    }
                });


    };

    private String toStringUserId(long id) {

        String strId = Integer.toString((int) id);

        return strId;

    }


}
