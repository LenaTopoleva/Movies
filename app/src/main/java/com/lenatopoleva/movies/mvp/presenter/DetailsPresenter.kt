package com.lenatopoleva.movies.mvp.presenter

import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.view.DetailsView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsPresenter(val movie: Movie): MvpPresenter<DetailsView>()  {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setTitle(movie.title)
        movie.overview?.let { viewState.setAbout(movie.overview) }
        movie.posterPath?.let { viewState.loadImage(movie.posterPath) }
        movie.posterPath?.let { viewState.loadBackdropImage(movie.backdropPath) }
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}