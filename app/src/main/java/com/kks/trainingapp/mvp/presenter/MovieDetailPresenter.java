package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.mvp.view.MovieDetailView;

public interface MovieDetailPresenter {
    void onUIReady();
    void onAttachView(MovieDetailView view);
    void showMovieDetailById(int id);
}