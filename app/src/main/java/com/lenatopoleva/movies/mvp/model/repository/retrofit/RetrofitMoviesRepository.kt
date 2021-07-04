package com.lenatopoleva.movies.mvp.model.repository.retrofit

import com.lenatopoleva.movies.BuildConfig.MOVIE_DB_API_KEY
import com.lenatopoleva.movies.mvp.model.api.IDataSource
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.entity.OriginalLanguage
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitMoviesRepository(val api: IDataSource): IMoviesRepository {

    override fun getMovies(page: Int): Single<List<Movie>> =
        api.getPopularMovies(MOVIE_DB_API_KEY, page).flatMap { response ->
            Single.just(response.results)
        }.subscribeOn(Schedulers.io())

}