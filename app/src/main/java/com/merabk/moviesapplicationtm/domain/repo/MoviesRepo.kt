package com.merabk.moviesapplicationtm.domain.repo

import com.merabk.moviesapplicationtm.domain.model.ContentDomainModel

interface MoviesRepo {
    suspend fun getAllMovies(): Result<List<ContentDomainModel>>
}