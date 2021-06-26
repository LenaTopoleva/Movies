package com.lenatopoleva.movies.mvp.model.repository.retrofit

import com.lenatopoleva.movies.BuildConfig.MOVIE_DB_API_KEY
import com.lenatopoleva.movies.mvp.model.api.IDataSource
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.entity.OriginalLanguage
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitMoviesRepository(val api: IDataSource): IMoviesRepository {
//    override fun getMovies(page: Int): List<Movie> {
//        val list: MutableList<Movie> = mutableListOf()
//        for (index in 1..20) {
//            val title = "Luca1111111111111111 #$index"
//            list.add(
//                Movie (false,"/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
//                    listOf(16,35,10751,14),508943, OriginalLanguage.En,title,
//                    "Luca and his best friend Alberto...",
//                    5135.463,"/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg","2021-06-17",
//                    "Luca",false,8.3,969)
//            )
//        }
//        return list.toList()
//    }


    override fun getMovies(page: Int): Single<List<Movie>> =
        api.getPopularMovies(MOVIE_DB_API_KEY, page).flatMap { response ->
            Single.just(response.results)
        }.subscribeOn(Schedulers.io())

}