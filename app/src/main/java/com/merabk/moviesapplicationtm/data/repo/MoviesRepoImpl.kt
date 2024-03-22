package com.merabk.moviesapplicationtm.data.repo

import com.merabk.moviesapplicationtm.data.service.MoviesApi
import com.merabk.moviesapplicationtm.domain.model.ContentDomainModel
import com.merabk.moviesapplicationtm.domain.repo.MoviesRepo
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesRepo {
    override suspend fun getAllMovies(): Result<List<ContentDomainModel>> {
        return try {
            val response = api.getAllMoviesList(API_KEY)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val contentApiModels = body.results.map {
                    ContentDomainModel(
                        id = it.id ?: 0,
                        posterPath = it.posterPath ?: "",
                        voteAverage = it.voteAverage ?: 0.0,
                        originalName = it.originalName ?: "",
                        overview = it.overview ?: ""
                    )
                }
                Result.success(contentApiModels)
            } else {
                Result.failure(Exception("Network call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        const val API_KEY = "f01ae87f32faec4d78371bb9347513bf"
    }

}