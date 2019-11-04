package com.kks.trainingapp.interactor;

import com.kks.trainingapp.model.IMovieDetailModel;
import com.kks.trainingapp.model.IMovieListModel;
import com.kks.trainingapp.model.MovieDetailModel;
import com.kks.trainingapp.model.MovieDetailModelImpl;
import com.kks.trainingapp.model.MovieListModel;
import com.kks.trainingapp.model.MovieListModelImpl;
import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public class MovieDetailInteractor {
    private ServiceHelper.ApiService mService;
    private IMovieDetailModel movieDetailModel;

    public MovieDetailInteractor(ServiceHelper.ApiService service) {
        this.mService = service;
        movieDetailModel = new MovieDetailModelImpl();
    }

    public Observable<MovieDetailModel> getMovieDetailById(int id) {
        return this.movieDetailModel.getMovieDetailFromApi(mService, id);

    }
}
