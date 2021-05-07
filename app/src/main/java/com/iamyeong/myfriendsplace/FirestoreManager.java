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
    private static String downsideCollectionId;
    private static String userCollectionId;
    private ArrayList<Post> arrayList = new ArrayList<>();
    private ArrayList<Comment> commentList = new ArrayList<>();
    private Context context;
    protected OnGetPostsListener mListener;
    protected OnGetCommentListener mCommentListener;

    private FirestoreManager() {}

    public static FirestoreManager getInstance(Context context) {

        if (firestoreManager == null) {
            firestoreManager = new FirestoreManager();

            db = FirebaseFirestore.getInstance();
            storageKey = context.getString(R.string.collection_name);
            downsideCollectionId = context.getString(R.string.down_collection_name);
            userCollectionId = context.getString(R.string.users);

        }

        return firestoreManager;

    }

    public ArrayList<Post> getPostList() {
        return arrayList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void addPost(Post post) {

        //Auto document id construct
        db.collection(storageKey)
                .add(post);

    }

    public ArrayList<Post> requestPosts() {

        return arrayList;
    }

    public void getPosts(OnGetPostsListener listener) {

        //ArrayList<Post> postList = new ArrayList<>();

       mListener = listener;

        if(arrayList != null) {

            arrayList.clear();
        }


        //Get all document at this collection.
        db.collection(storageKey)
                //.whereEqualTo() <-- this configuration criteria
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            //postList.clear();
                            for ( QueryDocumentSnapshot document : task.getResult()) {

                                String title = (String) document.getData().get("title");
                                String content = (String) document.getData().get("content");
                                long publisherId = (Long) document.getData().get("publisher");
                                long times = (Long) document.getData().get("times");
                                String documentId = document.getId();

                                System.out.println("FirestoreManager : " + documentId + ", " + publisherId + ", " + title + ", " + content + ", " + times);

                                Post post = new Post(documentId, publisherId, title, content, times);

                                arrayList.add(post);

                            }

                            mListener.onGetPosts(arrayList);

                        }

                    }
                });



    }

    public void editPost() {


    }


    public void deletePost() {

    }


    public void addComment(String documentId, Comment comment, OnGetCommentListener listener) {

        db.collection(storageKey).document(documentId)
                .collection(downsideCollectionId)
                .add(comment);

        mCommentListener = listener;
        mCommentListener.OnAddedComment();

    }

    public void editComment() {

    }

    public void getComments(OnGetCommentListener commentListener, String documentId) {

        mCommentListener = commentListener;

        if (commentList != null) {
            commentList.clear();
        }

        db.collection(storageKey).document(documentId)
                .collection(downsideCollectionId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                long time = (Long) document.getData().get("times");
                                long userId = (Long) document.getData().get("userKakaoId");
                                String content = (String) document.getData().get("comment");

                                System.out.println(time + ", " + userId + ", " + content);

                                Comment comment = new Comment(userId, content, time);

                                commentList.add(comment);


                            }

                            mCommentListener.OnGetComment(commentList);

                        }

                    }
                });


    }

    public void deleteComment() {

    }



}
