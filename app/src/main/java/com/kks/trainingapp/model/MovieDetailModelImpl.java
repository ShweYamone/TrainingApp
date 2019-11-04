package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.kks.trainingapp.util.AppConstant.DEVELOPER_KEY;

public class MovieDetailModelImpl implements IMovieDetailModel {
    @Override
    public Observable<MovieDetailModel> getMovieDetailFromApi(ServiceHelper.ApiService service, int id) {

        return service.getMovieDetail(id, DEVELOPER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
