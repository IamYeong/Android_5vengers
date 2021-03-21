package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;



public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView login = findViewById(R.id.img_login_kakao);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }



}