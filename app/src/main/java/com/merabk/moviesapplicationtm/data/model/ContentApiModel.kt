package com.merabk.moviesapplicationtm.data.model

import com.google.gson.annotations.SerializedName

data class ContentApiModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: List<ResultApi> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)
