package com.iamyeong.myfriendsplace;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(GlobalApplication.this, "166f21846686fa4df0ab109301328b70");


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
