package com.lenatopoleva.movies.di.modules

import com.lenatopoleva.movies.mvp.model.api.IDataSource
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import com.lenatopoleva.movies.mvp.model.repository.retrofit.RetrofitMoviesRepository
import com.lenatopoleva.movies.mvp.presenter.MoviesPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.terrakok.cicerone.Router

@Module
class MoviesScreenModule {

    @Provides
    fun moviesRepository(api: IDataSource): IMoviesRepository =
        RetrofitMoviesRepository(api)

    @Provides
    fun moviesPresenter(router: Router,
                        moviesRepository: IMoviesRepository,
                        uiScheduler: Scheduler ): MoviesPresenter =
        MoviesPresenter(router, moviesRepository, uiScheduler)

}