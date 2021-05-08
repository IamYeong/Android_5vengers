package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import com.kakao.sdk.user.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SplashActivity extends AppCompatActivity implements OnGetUserNameListener, OnGetPostsListener {

    private FirestoreManager firestoreManager;
    private UserManager userManager;
    private int loadCount = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firestoreManager = FirestoreManager.getInstance(SplashActivity.this);
        userManager = UserManager.getInstance();
        firestoreManager.getPosts(SplashActivity.this);
        userManager.loadUserName(SplashActivity.this);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onGetUserName() {
        loadCount++;

        if (loadCount == 2) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onGetPosts(ArrayList<Post> postList) {
        loadCount++;

        if (loadCount == 2) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


}