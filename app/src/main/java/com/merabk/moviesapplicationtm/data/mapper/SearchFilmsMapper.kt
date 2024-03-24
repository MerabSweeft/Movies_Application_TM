package com.merabk.moviesapplicationtm.data.mapper

import com.merabk.moviesapplicationtm.data.model.SearchResultApi
import com.merabk.moviesapplicationtm.data.repo.MoviesRepoImpl
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import dagger.Reusable
import javax.inject.Inject

interface SearchFilmsMapper {
    fun map(allMoviesModel: List<SearchResultApi>): List<MainContentDomainModel>

    @Reusable
    class SearchFilmsMapperImp @Inject constructor() : SearchFilmsMapper {
        override fun map(allMoviesModel: List<SearchResultApi>): List<MainContentDomainModel> =
            allMoviesModel.map {
                MainContentDomainModel(
                    id = it.id,
                    overview = it.overview,
                    name = it.name,
                    voteAverage = it.voteAverage,
                    posterPath = (MoviesRepoImpl.IMAGE_BEGINNING + it.posterPath.orEmpty())
                )
            }
    }
}