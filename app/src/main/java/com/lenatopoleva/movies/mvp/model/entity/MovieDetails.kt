package com.lenatopoleva.movies.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MovieDetails (

    @Expose val adult: Boolean,

    @Expose val backdropPath: String,

//    val belongsToCollection: Any? = null,

    @Expose val budget: Long,
    @Expose val genres: List<Genre>,
    @Expose val homepage: String,
    @Expose val id: Long,

    @Expose val imdbID: String,

    @Expose val originalLanguage: String,

    @Expose val originalTitle: String,

    @Expose val overview: String,
    @Expose val popularity: Double,

    @Expose val posterPath: String?,

    @Expose val productionCompanies: List<ProductionCompany>,

    @Expose val productionCountries: List<ProductionCountry>,

    @Expose val releaseDate: String,

    @Expose val revenue: Long,
    @Expose val runtime: Long,

    @Expose val spokenLanguages: List<SpokenLanguage>,

    @Expose val status: String,
    @Expose val tagline: String,
    @Expose val title: String,
    @Expose val video: Boolean,

    @Expose val voteAverage: Double,

    @Expose val voteCount: Long
    ): Parcelable

@Parcelize
data class Genre (
        @Expose val id: Long,
        @Expose val name: String
    ): Parcelable

@Parcelize
data class ProductionCompany (
        @Expose val id: Long,
        @Expose val logoPath: String? = null,
        @Expose val name: String,
        @Expose val originCountry: String
    ): Parcelable

@Parcelize
data class ProductionCountry (
        @Expose val iso3166_1: String,
        @Expose val name: String
    ): Parcelable

@Parcelize
data class SpokenLanguage (
        @Expose val englishName: String,
        @Expose val iso639_1: String,
        @Expose val name: String
    ): Parcelable
