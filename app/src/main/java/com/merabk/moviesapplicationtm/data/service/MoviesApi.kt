package com.merabk.moviesapplicationtm.data.service

import com.merabk.moviesapplicationtm.data.model.ContentApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApi {
    @GET(
        "discover/tv?include_adult=false&language=en-US&page=1&sort_by=popularity.desc&"
    )
    suspend fun getAllMoviesList(
        @Query("api_key") apiKey: String,
    ): Response<ContentApiModel>
}