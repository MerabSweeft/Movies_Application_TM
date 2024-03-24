package com.merabk.moviesapplicationtm.data.repo

import com.merabk.moviesapplicationtm.data.mapper.AllFilmsMapper
import com.merabk.moviesapplicationtm.data.mapper.FilmDetailsMapper
import com.merabk.moviesapplicationtm.data.mapper.SearchFilmsMapper
import com.merabk.moviesapplicationtm.data.service.MoviesApi
import com.merabk.moviesapplicationtm.domain.model.DetailsContentDomainModel
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import com.merabk.moviesapplicationtm.domain.repo.MoviesRepo
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val api: MoviesApi,
    private val allFilmsMapper: AllFilmsMapper,
    private val searchFilmsMapper: SearchFilmsMapper,
    private val filmDetailsMapper: FilmDetailsMapper,
) : MoviesRepo {
    override suspend fun getAllMovies(): Result<List<MainContentDomainModel>> {
        return try {
            val response = api.getAllMoviesList()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val contentApiModels = allFilmsMapper.map(body.results)
                Result.success(contentApiModels)
            } else {
                Result.failure(Exception("Network call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchMovies(query: String): Result<List<MainContentDomainModel>> {
        return try {
            val response = api.searchMovies(query = query)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val contentApiModels = searchFilmsMapper.map(body.results)
                Result.success(contentApiModels)
            } else {
                Result.failure(Exception("Network call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }    }

    override suspend fun getMovieDetails(id: Int): Result<DetailsContentDomainModel> {
        return try {
            val response =
                api.getMovieDetails(movieId = id)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val contentApiModels = filmDetailsMapper.map(body)
                Result.success(contentApiModels)
            } else {
                Result.failure(Exception("Network call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        const val IMAGE_BEGINNING = "https://image.tmdb.org/t/p/w500"
    }

}