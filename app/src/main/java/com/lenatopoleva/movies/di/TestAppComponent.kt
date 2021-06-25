package com.lenatopoleva.movies.di

import com.lenatopoleva.movies.di.modules.testModules.TestImageLoaderModule
import com.lenatopoleva.movies.ui.fragment.DetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestImageLoaderModule::class,
])

interface TestAppComponent {
    fun inject(detailsFragment: DetailsFragment)
}