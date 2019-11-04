package com.kks.trainingapp.interactor;

import com.kks.trainingapp.model.LoginRequestBody;
import com.kks.trainingapp.model.ProfileInfoModel;
import com.kks.trainingapp.model.ProfileInfoModelImpl;
import com.kks.trainingapp.model.RequestTokenBody;
import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public class LoginInteractor {

    private ServiceHelper.ApiService mService;
    private ProfileInfoModelImpl mProfileInfoModelImpl;

    public LoginInteractor(ServiceHelper.ApiService service) {
        this.mService = service;
        this.mProfileInfoModelImpl = new ProfileInfoModelImpl();
    }

    public Observable<ProfileInfoModel> getRequestToken() {

        return mProfileInfoModelImpl.getRequestTokenFromApi(mService);
    }

    public Observable<ProfileInfoModel> getLoginValidate(LoginRequestBody requestBody) {

        return mProfileInfoModelImpl.getLoginValidteFromApi(mService,requestBody);
    }

    public Observable<ProfileInfoModel> getSession(RequestTokenBody requestTokenBody) {
        return mProfileInfoModelImpl.getSessionByRequestTokenFromApi(mService, requestTokenBody);
    }

}
