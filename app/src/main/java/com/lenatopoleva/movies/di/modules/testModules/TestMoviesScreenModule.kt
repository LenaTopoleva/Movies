package com.lenatopoleva.movies.di.modules.testModules

import com.lenatopoleva.movies.mvp.model.api.IDataSource
import com.lenatopoleva.movies.mvp.model.repository.IMoviesRepository
import com.lenatopoleva.movies.mvp.model.repository.retrofit.RetrofitMoviesRepository
import com.lenatopoleva.movies.mvp.model.repository.testRepository.TestMoviesRepository
import com.lenatopoleva.movies.mvp.presenter.MoviesPresenter
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module
class TestMoviesScreenModule {

    @Provides
    @Named("testMoviesRepository")
    fun moviesRepository(): IMoviesRepository =
        TestMoviesRepository()

    @Provides
    fun moviesPresenter(router: Router,
                        @Named("testMoviesRepository") moviesRepository: IMoviesRepository,
                        uiScheduler: Scheduler
    ): MoviesPresenter =
        MoviesPresenter(router, moviesRepository, uiScheduler)

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

}