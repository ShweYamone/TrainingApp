package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.mvp.view.LoginView;
import com.kks.trainingapp.mvp.view.MainView;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public interface MainPresenter {

    void onUIReady();
    void onAttachView(MainView view);
    void getNowPlayingMovies();
    void getNowPlayingMoviesByPaging(int page);
}
