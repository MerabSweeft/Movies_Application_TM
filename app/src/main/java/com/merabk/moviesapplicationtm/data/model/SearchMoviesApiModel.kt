package com.merabk.moviesapplicationtm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMoviesApiModel(
    @SerialName("page") val page: Int? = null,
    @SerialName("results") val results: List<SearchResultApi> = arrayListOf(),
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null
)

