package com.lenatopoleva.movies.mvp.model.repository.testRepository

import com.lenatopoleva.movies.BuildConfig
import com.lenatopoleva.movies.mvp.model.api.IDataSource
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.entity.OriginalLanguage
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TestMoviesRepository(): IMoviesRepository {

    val list: MutableList<Movie> = mutableListOf()

    override fun getMovies(page: Int): Single<List<Movie>> {
        list.clear()
        for (index in 1..20) {
            list.add(
                Movie (false,"/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
                    listOf(16,35,10751,14),508943, OriginalLanguage.En,"Luca #$index",
                    "Luca and his best friend Alberto...",
                    5135.463,"/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg","2021-06-17",
                    "Luca",false,8.3,969)
            )
        }
        return Single.just(list.toList()).subscribeOn(Schedulers.io())
    }

//    override fun getMovies(page: Int): List<Movie> {
//        list.clear()
//        for (index in 1..20) {
//            val title = "Luca #$index"
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

}