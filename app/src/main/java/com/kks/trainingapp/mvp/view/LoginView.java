package com.kks.trainingapp.mvp.view;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public interface LoginView extends BaseView {

    void saveLoginData(String sessionId);
    void onLoginComplete();
    void checkLogin();
}
