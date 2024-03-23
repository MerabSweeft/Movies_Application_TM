package com.merabk.moviesapplicationtm.data.mapper

import com.merabk.moviesapplicationtm.data.model.MovieDetailsApiModel
import com.merabk.moviesapplicationtm.data.repo.MoviesRepoImpl
import com.merabk.moviesapplicationtm.domain.model.DetailsContentDomainModel
import com.merabk.moviesapplicationtm.domain.model.ProductionCompaniesDomainModel
import dagger.Reusable
import javax.inject.Inject

interface FilmDetailsMapper {
    fun map(body: MovieDetailsApiModel): DetailsContentDomainModel

    @Reusable
    class FilmDetailsMapperImpl @Inject constructor() : FilmDetailsMapper {
        override fun map(body: MovieDetailsApiModel): DetailsContentDomainModel {
            val contentApiModels = DetailsContentDomainModel(
                id = body.id ?: 0,
                posterPath = (MoviesRepoImpl.IMAGE_BEGINNING + body.posterPath),
                voteAverage = body.voteAverage ?: 0.0,
                originalName = body.originalTitle ?: "",
                overview = body.overview ?: "",
                productionCompanies = body.productionCompanies.map {
                    ProductionCompaniesDomainModel(
                        id = it.id ?: 0,
                        logoPath = it.logoPath ?: "",
                        name = it.name ?: "",
                        originCountry = it.originCountry ?: ""
                    )
                },
                genres = body.genres.toString()
            )
            return contentApiModels
        }
    }
}