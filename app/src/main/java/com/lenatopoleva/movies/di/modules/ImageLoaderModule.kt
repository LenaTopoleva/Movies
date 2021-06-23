package com.lenatopoleva.movies.di.modules

import android.widget.ImageView
import com.lenatopoleva.movies.mvp.model.imageloader.IImageLoader
import com.lenatopoleva.movies.ui.imageloader.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    private val _imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    @Named("imageBaseUrl")
    @Provides
    fun baseUrl(): String {
        return _imageBaseUrl
    }

    @Singleton
    @Provides
    fun imageLoader(@Named("imageBaseUrl") imageBaseUrl: String): IImageLoader<ImageView> = GlideImageLoader(imageBaseUrl)
}