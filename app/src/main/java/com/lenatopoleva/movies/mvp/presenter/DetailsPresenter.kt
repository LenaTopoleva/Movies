package com.lenatopoleva.movies.mvp.presenter

import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.view.DetailsView
import com.lenatopoleva.movies.ui.App
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class DetailsPresenter(val movie: Movie): MvpPresenter<DetailsView>()  {

    @Inject
    lateinit var router: Router

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setTitle(movie.title)
        movie.overview?.let { viewState.setAbout(movie.overview) }
        movie.posterPath?.let { viewState.loadImage(movie.posterPath) }
        if (movie.backdropPath != null)  viewState.loadBackdropImage(movie.backdropPath)
        else viewState.loadBackdropImage(movie.posterPath)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}