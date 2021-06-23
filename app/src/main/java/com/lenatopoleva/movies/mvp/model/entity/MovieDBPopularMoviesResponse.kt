package com.lenatopoleva.movies.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDBPopularMoviesResponse (
    @Expose val page: Long,
    @Expose val results: List<Movie>,
    @Expose val totalPages: Long,
    @Expose val totalResults: Long
    ): Parcelable

@Parcelize
data class Movie (
    @Expose val adult: Boolean,
    @Expose val backdropPath: String?,
    @Expose val genreIDS: List<Long>,

    @Expose val id: Long,

    @Expose val originalLanguage: OriginalLanguage,

    @Expose val originalTitle: String,

    @Expose val overview: String?,
    @Expose val popularity: Double,

    @Expose val posterPath: String?,

    @Expose val releaseDate: String,

    @Expose val title: String,
    @Expose val video: Boolean,

    @Expose val voteAverage: Double,

    @Expose val voteCount: Long
): Parcelable

enum class OriginalLanguage(val value: String) {
    En("en"),
    Es("es");

    companion object {
        public fun fromValue(value: String): OriginalLanguage = when (value) {
            "en" -> En
            "es" -> Es
            else -> throw IllegalArgumentException()
        }
    }
}
