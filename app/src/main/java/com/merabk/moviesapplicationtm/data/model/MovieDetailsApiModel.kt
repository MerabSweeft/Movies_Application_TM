package com.merabk.moviesapplicationtm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDetailsApiModel(
    @SerialName("adult") val adult: Boolean?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("belongs_to_collection") val belongsToCollection: BelongsToCollection? = BelongsToCollection(),
    @SerialName("budget") val budget: Int?,
    @SerialName("genres") val genres: ArrayList<Genres> = arrayListOf(),
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int?,
    @SerialName("imdb_id") val imdbId: String?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("original_title") val originalTitle: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: ArrayList<ProductionCompanies> = arrayListOf(),
    @SerialName("production_countries") val productionCountries: ArrayList<ProductionCountries> = arrayListOf(),
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("revenue") val revenue: Int?,
    @SerialName("runtime") val runtime: Int?,
    @SerialName("spoken_languages") val spokenLanguages: ArrayList<SpokenLanguages> = arrayListOf(),
    @SerialName("status") val status: String?,
    @SerialName("tagline") val tagline: String?,
    @SerialName("title") val title: String?,
    @SerialName("video") val video: Boolean?,
    @SerialName("vote_average") val voteAverage: Double?,
    @SerialName("vote_count") val voteCount: Int?
)
