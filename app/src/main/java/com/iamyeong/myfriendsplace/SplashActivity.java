package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.kakao.sdk.user.model.User;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements OnGetUserNameListener, OnGetPostsListener {

    private FirestoreManager firestoreManager;
    private UserManager userManager;
    private int loadCount = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();

        firestoreManager = FirestoreManager.getInstance(SplashActivity.this);
        userManager = UserManager.getInstance();
        firestoreManager.getPosts(SplashActivity.this);
        userManager.loadUserName(SplashActivity.this);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler = new Handler(getMainLooper());

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        while (loadCount != 2) {
                            System.out.println(loadCount);
                        }

                        /*
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                         */

                    }
                });

            }
        });


        thread.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onGetUserName() {
        loadCount++;
        System.out.println("loadCount : " + loadCount);

    }

    @Override
    public void onGetPosts(ArrayList<Post> postList) {
        loadCount++;
        System.out.println("loadCount : " + loadCount);
    }


}