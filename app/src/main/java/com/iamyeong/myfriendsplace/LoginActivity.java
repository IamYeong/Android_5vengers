package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Profile;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;


public class LoginActivity extends AppCompatActivity {


    private String knickName, imageURL, thumbnailURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         Function2<OAuthToken, Throwable, Unit> function2 = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                if (oAuthToken != null) {
                    //Detected account to do
                } else {
                    //Undetected account to do
                }

                checkKakaoLogin();

                return null;
            }
        };

        ImageView loginButton = findViewById(R.id.img_login_kakao);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    //Meaning of return true : Already install kakao talk application.

                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, function2);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, function2);
                }

            }
        });

    }

    private void checkKakaoLogin() {

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {

                if (user != null) {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    knickName = user.getKakaoAccount().getProfile().getNickname();
                    imageURL = user.getKakaoAccount().getProfile().getProfileImageUrl();
                    thumbnailURL = user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                    intent.putExtra("NAME", knickName);
                    intent.putExtra("IMAGE", imageURL);
                    intent.putExtra("THUMBNAIL", thumbnailURL);

                    startActivity(intent);
                    finish();


                } else {
                    Toast.makeText(LoginActivity.this, R.string.login_fail_5, Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        });


    }



}