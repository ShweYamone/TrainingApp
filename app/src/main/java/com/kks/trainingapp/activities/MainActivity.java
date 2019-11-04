package com.kks.trainingapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kks.trainingapp.R;
import com.kks.trainingapp.adapters.MovieAdapter;
import com.kks.trainingapp.common.BaseActivity;
import com.kks.trainingapp.common.ItemOffsetDecoration;
import com.kks.trainingapp.common.SmartScrollListener;
import com.kks.trainingapp.custom_control.MyanProgressDialog;
import com.kks.trainingapp.interactor.MovieInteractor;
import com.kks.trainingapp.model.MovieInfoModel;
import com.kks.trainingapp.mvp.presenter.MainPresenterImpl;
import com.kks.trainingapp.mvp.view.MainView;
import com.kks.trainingapp.util.ServiceHelper;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.cv_data_error)
    CardView cvDataError;

    @BindView(R.id.recycler_movie)
    RecyclerView recyclerMovie;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MovieAdapter mAdapter;

    private ServiceHelper.ApiService mService;

    private MainPresenterImpl mPresenter;

    private SmartScrollListener mSmartScrollListener;

    private MyanProgressDialog mDialog;

    private int page = 1;

    public static Intent getMainActivityIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {

        setupToolbar(false);
        setupToolbarText("Movie");
        init();
    }

    private void init() {
        mAdapter = new MovieAdapter();

        mDialog = new MyanProgressDialog(this);

        mService = ServiceHelper.getClient(this);

        mPresenter = new MainPresenterImpl(new MovieInteractor(mService));

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {

                page++;
                mPresenter.getNowPlayingMoviesByPaging(page);

            }
        });

        recyclerMovie.setHasFixedSize(true);
        recyclerMovie.setLayoutManager(new GridLayoutManager(this,3));
        recyclerMovie.addItemDecoration(new ItemOffsetDecoration(3));
        recyclerMovie.setAdapter(mAdapter);
        recyclerMovie.addOnScrollListener(mSmartScrollListener);

        swipeRefreshLayout.setOnRefreshListener(this);

        mPresenter.onAttachView(this);
        mPresenter.onUIReady();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onTerminate();
    }

    @Override
    public void addMoreMoviesToTheList(List<MovieInfoModel> movieInfoModelList) {
        for (MovieInfoModel model: movieInfoModelList) {
            mAdapter.add(model);
        }
    }

    @Override
    public void showMovieList(List<MovieInfoModel> movieInfoModelList) {

        cvDataError.setVisibility(View.GONE);

        page = 1;

        mAdapter.clear();
     //   mAdapter.showLoading();
        for (MovieInfoModel model: movieInfoModelList) {
            mAdapter.add(model);
        }
     //   mAdapter.clearFooter();

    }

    @Override
    public void resetPageNumberToDefault() {
        page--;
    }

    @Override
    public void showNoMovieInfo() {
        cvDataError.setVisibility(View.VISIBLE);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showLoading() {
        if (!isFinishing()) {
            mDialog.showDialog();
        }
    }

    @Override
    public void hideLoading() {
        if (!isFinishing()) {
            mDialog.hideDialog();
        }
    }

    @Override
    public void showToastMsg(String msg) {
        this.hideLoading();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMsg(String title, String msg) {
        this.hideLoading();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        this.mPresenter.getNowPlayingMovies();
    }
}
