package com.merabk.moviesapplicationtm.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AllMoviesApiModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: List<ResultApi> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)

