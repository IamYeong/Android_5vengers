package com.iamyeong.myfriendsplace;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PostListGetter {

    private List<MyCardView> arrayList = new ArrayList<>();

    public PostListGetter() {

    }

    public List<MyCardView> getAllPost() {

        //for문을 활용해서 get()을 계속 호출하여 arrayList에 추가해나가는 방식도 괜찮을 듯.

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("FIRST")
                .document()
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        //toObject

                    }
                });

        return arrayList;
    }


}
