package com.kks.trainingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.kks.trainingapp.R;
import com.kks.trainingapp.common.BaseActivity;
import com.kks.trainingapp.custom_control.MyanButton;
import com.kks.trainingapp.custom_control.MyanEditText;
import com.kks.trainingapp.custom_control.MyanProgressDialog;
import com.kks.trainingapp.interactor.LoginInteractor;
import com.kks.trainingapp.mvp.presenter.LoginPresenterImpl;
import com.kks.trainingapp.mvp.view.LoginView;
import com.kks.trainingapp.util.ServiceHelper;
import com.kks.trainingapp.util.SharePreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_password)
    MyanEditText etPassword;

    @BindView(R.id.et_user_name)
    MyanEditText etUsername;

    @BindView(R.id.btn_login)
    MyanButton btnLogin;

    private LoginPresenterImpl mPresenter;

    private MyanProgressDialog mDialog;

    private ServiceHelper.ApiService mService;

    private SharePreferenceHelper mSharePreferenceHelper;

    private static final String TAG = "LoginActivity";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {

        setupToolbar(false);
        setupToolbarText("Login");
        setupToolbarBgColor("#bff0cf");

        init();
    }

    private void init() {

        mService = ServiceHelper.getClient(this);

        mDialog = new MyanProgressDialog(this);

        mSharePreferenceHelper = new SharePreferenceHelper(this);

        mPresenter = new LoginPresenterImpl(new LoginInteractor(this.mService));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickLogin(etUsername.getMyanmarText(),
                        etPassword.getMyanmarText());
            }
        });

        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //final int DRAWABLE_LEFT = 0;
                // final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                // final int DRAWABLE_BOTTOM = 3;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Drawable drawableRight = etPassword.getCompoundDrawables()[DRAWABLE_RIGHT];

                        if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                            drawableRight.setTint(getResources().getColor(R.color.colorPrimaryDark));
                            //Show Password
                            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                        else{
                            drawableRight.setTint(getResources().getColor(R.color.colorLightBlack));
                            //Hide Password
                            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                        }

                        return true;
                    }
                }
                return false;
            }


        });


        mPresenter.onAttachView(this);

        mPresenter.onUIReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onTerminate();
    }


    @Override
    public void saveLoginData(String sessionId) {
        Log.e(TAG, "saveLoginData: " + sessionId );
        mSharePreferenceHelper.setLogin(sessionId);
    }

    @Override
    public void onLoginComplete() {
        this.startActivity(MainActivity.getMainActivityIntent(this));
        this.finish();
    }

    @Override
    public void checkLogin() {
        if (mSharePreferenceHelper.isLogin()) {
            onLoginComplete();
        }
    }

    @Override
    public void showLoading() {

        if (!isFinishing()) {
            mDialog.showDialog();
        }
    }

    @Override
    public void hideLoading() {

        if (!isFinishing()) {
            mDialog.hideDialog();
        }
    }

    @Override
    public void showToastMsg(String msg) {

        this.hideLoading();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMsg(String title,String msg) {

        this.hideLoading();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }

    @Override
    public Context context() {
        return this;
    }
}
