package com.merabk.moviesapplicationtm.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMoviesApiModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: List<SearchResultApi> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)

