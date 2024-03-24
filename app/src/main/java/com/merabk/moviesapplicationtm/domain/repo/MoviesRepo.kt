package com.merabk.moviesapplicationtm.domain.repo

import com.merabk.moviesapplicationtm.domain.model.DetailsContentDomainModel
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel

interface MoviesRepo {
    suspend fun getAllMovies(): Result<List<MainContentDomainModel>>
    suspend fun searchMovies(query: String): Result<List<MainContentDomainModel>>
    suspend fun getMovieDetails(id: Int): Result<DetailsContentDomainModel>
}