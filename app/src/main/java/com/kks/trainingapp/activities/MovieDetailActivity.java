package com.kks.trainingapp.activities;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.kks.trainingapp.R;
import com.kks.trainingapp.common.BaseActivity;
import com.kks.trainingapp.custom_control.MyanBoldTextView;
import com.kks.trainingapp.custom_control.MyanProgressDialog;
import com.kks.trainingapp.custom_control.MyanTextView;
import com.kks.trainingapp.interactor.MovieDetailInteractor;
import com.kks.trainingapp.model.Genre;
import com.kks.trainingapp.model.MovieDetailModel;
import com.kks.trainingapp.model.ProductionCompany;
import com.kks.trainingapp.mvp.presenter.MovieDetailPresenterImpl;
import com.kks.trainingapp.mvp.view.MovieDetailView;
import com.kks.trainingapp.util.ServiceHelper;

import java.util.ArrayList;

import static com.kks.trainingapp.util.AppConstant.BASE_IMG_URL;

import butterknife.BindView;

public class MovieDetailActivity extends BaseActivity implements MovieDetailView {

    @BindView(R.id.iv_movie_poster)
    ImageView ivMoviePoster;

    @BindView(R.id.mt_movie_title)
    MyanBoldTextView mtTitle;

    @BindView(R.id.mt_genres)
    MyanBoldTextView mtGenres;

    @BindView(R.id.mt_overview)
    MyanTextView mtOverView;

    @BindView(R.id.mt_tag_line)
    MyanTextView mtTagLine;

    @BindView(R.id.mt_status)
    MyanTextView mtStatus;

    @BindView(R.id.mt_release_date)
    MyanTextView mtReleaseDate;

    @BindView(R.id.mt_production)
    MyanTextView mtProduction;

    @BindView(R.id.layoutTagline)
    LinearLayout layoutTagLine;

    private ServiceHelper.ApiService mService;

    private MovieDetailPresenterImpl mPresenter;

    private MyanProgressDialog mDialog;

    private static int mMovieId;

    public static Intent getMainActivityIntent(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mMovieId = movieId;
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(true);
        setupToolbarText("");
        setupToolbarBgColor("#00000000");
        init();

    }

    private void init() {
        mService = ServiceHelper.getClient(this);

        mDialog = new MyanProgressDialog(this);

        mPresenter = new MovieDetailPresenterImpl(new MovieDetailInteractor(this.mService));

        mPresenter.onAttachView(this);
     //   showLoading();
        mPresenter.showMovieDetailById(mMovieId);

    }

    @Override
    public void showMovieDetail(MovieDetailModel movieDetailModel) {
        mtTitle.setMyanmarText(movieDetailModel.getTitle());

        Glide.with(this)
                .load(BASE_IMG_URL + movieDetailModel.getBackdrop_path())
                    .into(ivMoviePoster);


        ArrayList<Genre> genres = movieDetailModel.getGenres();
        String genreStr = "";
        for (Genre genre: genres) {
            genreStr += genre.getName() + "  `";
        }

        mtGenres.setMyanmarText(genreStr);

        mtOverView.setMyanmarText(movieDetailModel.getOverview());

        String tagline = movieDetailModel.getTagline();
        if(!tagline.equals(""))
            mtTagLine.setMyanmarText("#"+movieDetailModel.getTagline());
        else
             layoutTagLine.setVisibility(View.GONE);

        mtStatus.setMyanmarText(movieDetailModel.getStatus());

        mtReleaseDate.setMyanmarText(movieDetailModel.getRelease_date());

        ArrayList<ProductionCompany> companies = movieDetailModel.getProduction_companies();
        String companyStr = "";
        for (ProductionCompany company: companies) {
            companyStr += company.getName() + ", " + company.getOrigin_country() + "<br>";
        }


        mtProduction.setMyanmarText(Html.fromHtml(companyStr).toString());

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

}
