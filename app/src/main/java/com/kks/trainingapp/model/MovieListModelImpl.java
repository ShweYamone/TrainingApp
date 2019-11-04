package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.kks.trainingapp.util.AppConstant.DEVELOPER_KEY;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public class MovieListModelImpl implements IMovieListModel {
    @Override
    public Observable<MovieListModel> getNowShowingMoviesFromApi(ServiceHelper.ApiService service, int page) {

        return service.getNowShowingMovies(DEVELOPER_KEY, "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
