package com.kks.trainingapp.model;

import java.util.ArrayList;

public class MovieDetailModel {
    int id;
    String title;
    String backdrop_path;
    String overview;
    String tagline;
    String status;
    String release_date;
    ArrayList<ProductionCompany> production_companies;
    ArrayList<Genre> genres;

    public MovieDetailModel(int id, String title, String backdrop_path, String overview, String tagline, String status, String release_date, ArrayList<ProductionCompany> production_companies, ArrayList<Genre> genres) {
        this.id = id;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.tagline = tagline;
        this.status = status;
        this.release_date = release_date;
        this.production_companies = production_companies;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(ArrayList<ProductionCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
