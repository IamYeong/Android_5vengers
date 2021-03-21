package com.iamyeong.myfriendsplace;

import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;

public class SessionCallback implements ISessionCallback {

    @Override
    public void onSessionOpened() {

        UserManagement.getInstance()
                .me(new MeV2ResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("***SessionClosed : ", "" + errorResult);
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {

                        UserAccount kakaoAccount = result.getKakaoAccount();
                        Profile profile = kakaoAccount.getProfile();

                        if (profile != null) {

                            Log.d("Profile : ", "" + profile.getNickname());
                            Log.d("Profile : ", "" + profile.getProfileImageUrl());
                            Log.d("Profile : ", "" + profile.getThumbnailImageUrl());



                        }

                    }
                });


    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {

        Log.e("***SessionFailed", "onSessionOpenFailed");

    }

    /*
    1. Kakao.init() 으로 SDK 초기화
    2. KakaoAdapter로 요청
    3. SessionCallback 으로 응답확인 후 사용자 정보 확인
    
    
     */


    
}
