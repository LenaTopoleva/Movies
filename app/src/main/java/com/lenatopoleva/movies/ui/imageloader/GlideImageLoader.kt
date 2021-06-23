package com.lenatopoleva.movies.ui.imageloader

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.lenatopoleva.movies.mvp.model.imageloader.IImageLoader


class GlideImageLoader(private val imageBaseUrl: String): IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        val imgUrl = imageBaseUrl + url

    Glide.with(container.context)
        .asBitmap()
        .load(imgUrl)
        .centerCrop()
        .listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                //Do stuff with error
                return false
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                //Do stuff with result
                return false
            }
        })
        .into(container)
    }

    override fun loadWithRoundCornersInto(url: String, container: ImageView) {
        val imgUrl = imageBaseUrl + url
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(10))

        Glide.with(container.context)
            .asBitmap()
            .load(imgUrl)
            .apply(requestOptions)
            .into(container)

    }
}