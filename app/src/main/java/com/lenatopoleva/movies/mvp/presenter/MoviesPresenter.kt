package com.lenatopoleva.movies.mvp.presenter

import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import com.lenatopoleva.movies.mvp.presenter.list.IMoviesListPresenter
import com.lenatopoleva.movies.mvp.view.MoviesView
import com.lenatopoleva.movies.mvp.view.list.MovieItemView
import com.lenatopoleva.movies.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MoviesPresenter(): MvpPresenter<MoviesView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var moviesRepository: IMoviesRepository
    @Inject lateinit var uiScheduler: Scheduler

    class MoviesListPresenter : IMoviesListPresenter {
        override var itemClickListener: ((MovieItemView) -> Unit)? = null

        var movies = mutableListOf<Movie>()

        override fun bindView(view: MovieItemView) {
            val movie = movies[view.pos]
            view.setTitle(movie.title)
            println("FILM: ${movie.title}")
            movie.posterPath?.let { view.loadImage(it) }
        }
        override fun getCount() = movies.size

        var currentItem: Int = 0
    }

    val moviesListPresenter = MoviesListPresenter()
    var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadMovies(1)

        moviesListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.DetailsScreen(moviesListPresenter.movies[view.pos]))
            moviesListPresenter.currentItem = view.pos
        }
    }

    fun loadMovies(page: Int) {
        disposables.add(moviesRepository.getMovies(page)
            .retry(3)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    moviesListPresenter.movies.addAll(it)
                    println("FIRST FILM: ${moviesListPresenter.movies.first().title}")
                    viewState.updateMoviesList()
                },
                { println("onError: ${it.message}") }))
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun onViewCreated() {
        viewState.scrollListToCurrentPosition(moviesListPresenter.currentItem)
    }

}