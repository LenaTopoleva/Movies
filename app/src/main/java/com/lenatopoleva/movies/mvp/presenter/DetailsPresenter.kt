package com.lenatopoleva.movies.mvp.presenter

import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.view.DetailsView
import moxy.MvpPresenter

class DetailsPresenter(val movie: Movie): MvpPresenter<DetailsView>()  {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setTitle(movie.title)
        movie.overview?.let { viewState.setAbout(movie.overview) }
        movie.posterPath?.let { viewState.loadImage(movie.posterPath) }
        if (movie.backdropPath != null)  viewState.loadBackdropImage(movie.backdropPath)
        else viewState.loadBackdropImage(movie.posterPath)
    }

}