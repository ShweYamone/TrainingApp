package com.kks.trainingapp.mvp.view;

import android.content.Context;

/**
 * Created by kaungkhantsoe on 2019-10-19.
 **/
public interface BaseView {

    Context context();
    void showLoading();
    void hideLoading();
    void showToastMsg(String msg);
    void showDialogMsg(String title,String msg);
}
