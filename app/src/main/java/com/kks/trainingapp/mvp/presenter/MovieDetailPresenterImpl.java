package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.R;
import com.kks.trainingapp.interactor.MovieDetailInteractor;
import com.kks.trainingapp.model.MovieDetailModel;
import com.kks.trainingapp.mvp.view.MovieDetailView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieDetailPresenterImpl extends BasePresenter implements MovieDetailPresenter {

    private MovieDetailView mView;
    private MovieDetailInteractor mInteractor;

    public MovieDetailPresenterImpl(MovieDetailInteractor mInteractor) {
        this.mInteractor = mInteractor;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.mView = null;
    }

    @Override
    public void onUIReady() {
       // mView.showMovieDetail();
    }

    @Override
    public void onAttachView(MovieDetailView view) {
        this.mView = view;

    }

    @Override
    public void showMovieDetailById(int id) {

        mView.showLoading();

        this.mInteractor.getMovieDetailById(id)
                .subscribe(new Observer<MovieDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetailModel movieDetailModel) {
                        mView.showMovieDetail(movieDetailModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                                e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
