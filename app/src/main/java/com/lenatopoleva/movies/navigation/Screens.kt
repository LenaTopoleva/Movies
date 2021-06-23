package com.lenatopoleva.movies.navigation

import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.ui.fragment.DetailsFragment
import com.lenatopoleva.movies.ui.fragment.MoviesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class MoviesScreen(): SupportAppScreen(){
        override fun getFragment() = MoviesFragment.newInstance()
    }

    class DetailsScreen(private val movie: Movie): SupportAppScreen(){
        override fun getFragment() = DetailsFragment.newInstance(movie)
    }

}