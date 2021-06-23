package com.lenatopoleva.movies.mvp.model.api

import com.lenatopoleva.movies.mvp.model.entity.MovieDBPopularMoviesResponse
import com.lenatopoleva.movies.mvp.model.entity.MovieDetails
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDataSource {

    @GET(value = "movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Single<MovieDBPopularMoviesResponse>

    @GET(value = "movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Single<MovieDetails>

}