package com.lenatopoleva.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.presenter.MoviesPresenter
import com.lenatopoleva.movies.mvp.view.MoviesView
import com.lenatopoleva.movies.ui.App
import com.lenatopoleva.movies.ui.adapter.EndlessRecyclerViewScrollListener
import com.lenatopoleva.movies.ui.adapter.MoviesRvAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class MoviesFragment: MvpAppCompatFragment(), MoviesView {

    val presenter: MoviesPresenter by moxyPresenter {
        MoviesPresenter().apply{ App.instance.appComponent.inject(this)}
    }

    private var adapter: MoviesRvAdapter? = null
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun init() {
        if (adapter == null) adapter = MoviesRvAdapter(presenter.moviesListPresenter).apply { App.instance.appComponent.inject(
            this
        )}
        val gridLayoutManager = GridLayoutManager(context, 2)
        movies_recyclerview.layoutManager = gridLayoutManager
        scrollListener = EndlessRecyclerViewScrollListener(
            (presenter::loadMovies),
            gridLayoutManager
        )
        movies_recyclerview.addOnScrollListener(scrollListener)
        movies_recyclerview.adapter = adapter
    }

    override fun updateMoviesList() {
        println("NEW FILM LIST, first: ${presenter.moviesListPresenter.movies.first().title}")
        adapter?.notifyDataSetChanged()
    }

    override fun scrollListToCurrentPosition(currentItem: Int) {
        movies_recyclerview?.layoutManager?.scrollToPosition(currentItem)
    }

    override fun openDetailsFragment(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(movie)
        findNavController().navigate(action)
    }

}