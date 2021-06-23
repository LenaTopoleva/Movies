package com.lenatopoleva.movies.di

import com.lenatopoleva.movies.di.modules.*
import com.lenatopoleva.movies.mvp.presenter.DetailsPresenter
import com.lenatopoleva.movies.mvp.presenter.MainPresenter
import com.lenatopoleva.movies.mvp.presenter.MoviesPresenter
import com.lenatopoleva.movies.ui.activity.MainActivity
import com.lenatopoleva.movies.ui.adapter.MoviesRvAdapter
import com.lenatopoleva.movies.ui.fragment.DetailsFragment
import com.lenatopoleva.movies.ui.fragment.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NavigationModule::class,
    ApiModule::class,
    ImageLoaderModule::class,
    MoviesScreenModule::class,
    DetailsScreenModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(moviesPresenter: MoviesPresenter)
    fun inject(moviesRvAdapter: MoviesRvAdapter)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(detailsPresenter: DetailsPresenter)
}