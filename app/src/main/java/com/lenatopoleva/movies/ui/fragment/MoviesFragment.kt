package com.lenatopoleva.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.mvp.presenter.MoviesPresenter
import com.lenatopoleva.movies.mvp.view.MoviesView
import com.lenatopoleva.movies.ui.App
import com.lenatopoleva.movies.ui.BackButtonListener
import com.lenatopoleva.movies.ui.adapter.MoviesRvAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MoviesFragment: MvpAppCompatFragment(), MoviesView, BackButtonListener {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    val presenter: MoviesPresenter by moxyPresenter {
        MoviesPresenter().apply{ App.instance.appComponent.inject(this)}
    }

    private var adapter: MoviesRvAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_movies, container, false)

    override fun init() {
        if (adapter == null) adapter = MoviesRvAdapter(presenter.moviesListPresenter).apply { App.instance.appComponent.inject(this)}
        movies_recyclerview.layoutManager = GridLayoutManager(context, 2)
        movies_recyclerview.adapter = adapter
        println("MoviesFragment init, adapter = $adapter")
    }

    override fun updateMoviesList() {
        println("NEW FILM LIST, first: ${presenter.moviesListPresenter.movies.first().title}")
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}