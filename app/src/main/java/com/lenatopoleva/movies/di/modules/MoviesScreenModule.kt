package com.lenatopoleva.movies.di.modules

import com.lenatopoleva.movies.mvp.model.api.IDataSource
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import com.lenatopoleva.movies.mvp.model.repository.retrofit.RetrofitMoviesRepository
import dagger.Module
import dagger.Provides

@Module
class MoviesScreenModule {

    @Provides
    fun moviesRepository(api: IDataSource): IMoviesRepository =
        RetrofitMoviesRepository(api)

}