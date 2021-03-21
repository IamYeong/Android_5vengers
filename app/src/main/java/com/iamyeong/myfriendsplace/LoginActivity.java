package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

public class LoginActivity extends AppCompatActivity {

    private SessionCallback sessionCallback = new SessionCallback();
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView login = findViewById(R.id.img_login_kakao);
        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);



            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(sessionCallback);
    }
}