package com.lenatopoleva.movies.ui

import android.app.Application
import com.lenatopoleva.movies.di.AppComponent
import com.lenatopoleva.movies.di.DaggerAppComponent
import com.lenatopoleva.movies.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =  DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}