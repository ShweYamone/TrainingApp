package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.R;
import com.kks.trainingapp.interactor.MovieInteractor;
import com.kks.trainingapp.model.MovieInfoModel;
import com.kks.trainingapp.model.MovieListModel;
import com.kks.trainingapp.mvp.view.LoginView;
import com.kks.trainingapp.mvp.view.MainView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public class MainPresenterImpl extends BasePresenter implements MainPresenter {

    private MainView mView = null;
    private MovieInteractor mInteractor;

    public MainPresenterImpl(MovieInteractor interactor) {
        this.mInteractor = interactor;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        this.mView = null;
    }

    @Override
    public void onUIReady() {

        getNowPlayingMovies();
    }

    @Override
    public void onAttachView(MainView view) {
        this.mView = view;
    }

    @Override
    public void getNowPlayingMovies() {

        mView.showLoading();

        this.mInteractor.getNowShowingMovieList(1)
                .subscribe(new Observer<MovieListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposableOberver(d);

                    }

                    @Override
                    public void onNext(MovieListModel movieListModel) {

                        if (movieListModel != null) {

                            if (movieListModel.getResults().isEmpty()) {
                                mView.showNoMovieInfo();
                            } else {
                                mView.showMovieList(movieListModel.getResults());

                            }

                        } else {
                            mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                                    mView.context().getResources().getString(R.string.please_check_your_internet_connection));
                        }
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

    @Override
    public void getNowPlayingMoviesByPaging(int page) {

        mView.showLoading();

        this.mInteractor.getNowShowingMovieList(page)
                .subscribe(new Observer<MovieListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposableOberver(d);

                    }

                    @Override
                    public void onNext(MovieListModel movieListModel) {

                        if (movieListModel != null) {

                            if (movieListModel.getResults().isEmpty()) {
                                mView.resetPageNumberToDefault();
                            } else {
                                mView.addMoreMoviesToTheList(movieListModel.getResults());

                            }

                        } else {
                            mView.resetPageNumberToDefault();
                            mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                                    mView.context().getResources().getString(R.string.please_check_your_internet_connection));
                        }
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
