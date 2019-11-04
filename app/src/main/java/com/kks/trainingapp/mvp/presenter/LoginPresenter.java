package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.mvp.view.LoginView;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public interface LoginPresenter {

    void onUIReady();
    void onAttachView(LoginView view);

    void onClickLogin(String username,String password);
}
