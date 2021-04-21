package com.iamyeong.myfriendsplace;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserFormatter {

    private long userId;

    FirebaseFirestore db;

    //Default constructor
    public UserFormatter() {
        db = FirebaseFirestore.getInstance();
    }

    public String toUserName(long userKakaoId) {

        String name = "";

        db.collection("USERS").document("USERNAMES")
                .get();
        //Get field list(latest user profile name) -> switch context

        return name;

    }

}
