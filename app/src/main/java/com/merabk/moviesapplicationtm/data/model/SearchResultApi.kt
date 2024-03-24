package com.merabk.moviesapplicationtm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultApi(
    val id: Int,
    val overview: String,
    @SerialName("original_title")
    val name: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("poster_path")
    val posterPath: String?
)
