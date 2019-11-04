package com.kks.trainingapp.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kks.trainingapp.R;
import com.kks.trainingapp.activities.MovieDetailActivity;
import com.kks.trainingapp.common.BaseAdapter;
import com.kks.trainingapp.custom_control.MyanTextView;
import com.kks.trainingapp.model.MovieInfoModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kks.trainingapp.util.AppConstant.BASE_IMG_URL;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public class MovieAdapter extends BaseAdapter {
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);

        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieAdapter.ViewHolder)holder).bindView((MovieInfoModel)getItemsList().get(position),position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie_poster)
        ImageView ivMoviePoster;

        @BindView(R.id.mt_movie_title)
        MyanTextView mtMovieTitle;

        MovieInfoModel mMovieModel;

        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(MovieDetailActivity.getMainActivityIntent(context,mMovieModel.getId()));

                }
            });
        }

        public void bindView(MovieInfoModel model, int position) {

            this.mMovieModel = model;

            Glide.with(context)
                    .load(BASE_IMG_URL+model.getPoster_path())
                    .into(ivMoviePoster);

            mtMovieTitle.setMyanmarText(model.getTitle());

        }
    }
}
