package com.example.sirwarfox.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sir.WarFox on 11/02/2018.
 */

public class MoviesResponse {

    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer total_results;
    @SerializedName("total_pages")
    private Integer total_pages;
    @SerializedName("results")
    private List<Movie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
