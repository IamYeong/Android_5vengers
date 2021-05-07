package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kakao.sdk.user.model.User;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements OnGetUserNameListener, OnGetPostsListener {

    FirestoreManager firestoreManager;
    UserManager userManager;
    private int loadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firestoreManager = FirestoreManager.getInstance(SplashActivity.this);
        userManager = UserManager.getInstance();
        firestoreManager.getPosts(SplashActivity.this);
        userManager.loadUserName(SplashActivity.this);


        while (loadCount == 2) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onGetUserName() {
        loadCount++;
    }

    @Override
    public void onGetPosts(ArrayList<Post> postList) {
        loadCount++;
    }


}