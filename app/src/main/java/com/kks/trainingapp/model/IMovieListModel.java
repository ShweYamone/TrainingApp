package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public interface IMovieListModel {

    Observable<MovieListModel> getNowShowingMoviesFromApi(ServiceHelper.ApiService service, int id);
}
