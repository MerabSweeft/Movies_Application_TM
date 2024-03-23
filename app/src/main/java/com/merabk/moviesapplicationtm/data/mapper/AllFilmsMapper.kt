package com.merabk.moviesapplicationtm.data.mapper

import com.merabk.moviesapplicationtm.data.model.ResultApi
import com.merabk.moviesapplicationtm.data.repo.MoviesRepoImpl
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import dagger.Reusable
import javax.inject.Inject

interface AllFilmsMapper {
    fun map(allMoviesModel: List<ResultApi>): List<MainContentDomainModel>

    @Reusable
    class AllFilmsMapperImpl @Inject constructor() : AllFilmsMapper {
        override fun map(allMoviesModel: List<ResultApi>): List<MainContentDomainModel> {
            val contentApiModels = allMoviesModel.map {
                MainContentDomainModel(
                    id = it.id,
                    overview = it.overview,
                    name = it.name,
                    voteAverage = it.voteAverage,
                    posterPath = (MoviesRepoImpl.IMAGE_BEGINNING + it.posterPath.orEmpty())
                )
            }
            return contentApiModels
        }
    }
}