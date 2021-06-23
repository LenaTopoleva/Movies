package com.lenatopoleva.movies.mvp.model.imageloader

interface IImageLoader<T> {
    fun loadInto(url: String?, container: T)
    fun loadWithRoundCornersInto(url: String?, container: T)
}