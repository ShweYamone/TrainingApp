package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public interface IMovieDetailModel {
    Observable<MovieDetailModel> getMovieDetailFromApi(ServiceHelper.ApiService service, int page);
}
