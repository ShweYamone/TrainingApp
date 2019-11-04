package com.kks.trainingapp.mvp.view;

import com.kks.trainingapp.model.MovieInfoModel;

import java.util.List;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public interface MainView extends BaseView{

    void addMoreMoviesToTheList(List<MovieInfoModel> movieInfoModelList);
    void showMovieList(List<MovieInfoModel> movieInfoModelList);
    void resetPageNumberToDefault();
    void showNoMovieInfo();

}
