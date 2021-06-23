package com.lenatopoleva.movies.di.modules

import com.lenatopoleva.movies.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}