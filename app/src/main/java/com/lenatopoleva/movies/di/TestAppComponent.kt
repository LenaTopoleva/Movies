package com.lenatopoleva.movies.di

import com.lenatopoleva.movies.di.modules.AppModule
import com.lenatopoleva.movies.di.modules.NavigationModule
import com.lenatopoleva.movies.di.modules.testModules.TestImageLoaderModule
import com.lenatopoleva.movies.di.modules.testModules.TestMoviesScreenModule
import com.lenatopoleva.movies.ui.fragment.DetailsFragment
import com.lenatopoleva.movies.ui.fragment.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NavigationModule::class,
    TestImageLoaderModule::class,
    TestMoviesScreenModule::class
])

interface TestAppComponent: AppComponent {
    override fun inject(detailsFragment: DetailsFragment)
    override fun inject(moviesFragment: MoviesFragment)

}