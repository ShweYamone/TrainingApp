package com.kks.trainingapp.interactor;

import com.kks.trainingapp.model.IMovieListModel;
import com.kks.trainingapp.model.MovieListModel;
import com.kks.trainingapp.model.MovieListModelImpl;
import com.kks.trainingapp.util.ServiceHelper;

import java.util.List;

import io.reactivex.Observable;

import static com.kks.trainingapp.util.AppConstant.DEVELOPER_KEY;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public class MovieInteractor {

    private ServiceHelper.ApiService mService;
    private IMovieListModel movieListModel;

    public MovieInteractor(ServiceHelper.ApiService service) {
        this.mService = service;
        movieListModel = new MovieListModelImpl();
    }

    public Observable<MovieListModel> getNowShowingMovieList(int page) {
        return this.movieListModel.getNowShowingMoviesFromApi(mService,page);

    }
}
