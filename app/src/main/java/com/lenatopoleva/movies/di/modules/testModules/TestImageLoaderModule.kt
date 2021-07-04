package com.lenatopoleva.movies.di.modules.testModules

import android.widget.ImageView
import com.lenatopoleva.movies.di.modules.ImageLoaderModule
import com.lenatopoleva.movies.mvp.model.imageloader.IImageLoader
import com.lenatopoleva.movies.ui.imageloader.GlideImageLoader
import dagger.Module
import dagger.Provides
import io.mockk.mockk


@Module
class TestImageLoaderModule {

    @Provides
    fun imageLoader(): IImageLoader<ImageView>{
        return mockk<GlideImageLoader>(relaxed = true)
    }
}