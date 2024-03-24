package com.merabk.moviesapplicationtm.data.service

import com.merabk.moviesapplicationtm.data.model.AllMoviesApiModel
import com.merabk.moviesapplicationtm.data.model.MovieDetailsApiModel
import com.merabk.moviesapplicationtm.data.model.SearchMoviesApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/tv?include_adult=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getAllMoviesList(): Response<AllMoviesApiModel>

    @GET("search/movie?")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Response<SearchMoviesApiModel>

    @GET("movie/")
    suspend fun getMovieDetails(
        @Query("id") movieId: Int,
    ): Response<MovieDetailsApiModel>
}