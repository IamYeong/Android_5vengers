package com.iamyeong.myfriendsplace;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {

    private static GlobalApplication instance;

    public static GlobalApplication getGlobalApplicationContext() {

        if (instance == null) {

            Log.e("App context null : ", "instance is null!");

        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());
        Log.d("KakaoSDK", "init()");

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        instance = null;

    }
}
