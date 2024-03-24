package com.merabk.moviesapplicationtm.domain.usecase

import com.merabk.moviesapplicationtm.domain.repo.MoviesRepo
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repo: MoviesRepo) {
    suspend operator fun invoke(query: String) = repo.searchMovies(query = query)
}