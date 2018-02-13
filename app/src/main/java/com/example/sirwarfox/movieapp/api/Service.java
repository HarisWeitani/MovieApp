package com.example.sirwarfox.movieapp.api;

import com.example.sirwarfox.movieapp.model.MoviesResponse;
import com.example.sirwarfox.movieapp.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sir.WarFox on 11/02/2018.
 */

public interface Service {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
