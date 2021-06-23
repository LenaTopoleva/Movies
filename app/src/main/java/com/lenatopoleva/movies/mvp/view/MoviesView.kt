package com.lenatopoleva.movies.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MoviesView: MvpView {
    fun init()
    fun updateMoviesList()
}