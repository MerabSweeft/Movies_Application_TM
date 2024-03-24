package com.merabk.moviesapplicationtm.data.mapper

import com.merabk.moviesapplicationtm.data.model.MovieDetailsApiModel
import com.merabk.moviesapplicationtm.data.model.ProductionCompanies
import com.merabk.moviesapplicationtm.data.repo.MoviesRepoImpl
import com.merabk.moviesapplicationtm.domain.model.DetailsContentDomainModel
import com.merabk.moviesapplicationtm.domain.model.ProductionCompaniesDomainModel
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

interface FilmDetailsMapper {
    fun map(body: MovieDetailsApiModel): DetailsContentDomainModel

    @Reusable
    class FilmDetailsMapperImpl @Inject constructor() : FilmDetailsMapper {
        override fun map(body: MovieDetailsApiModel): DetailsContentDomainModel =
            DetailsContentDomainModel(
                id = body.id ?: 0,
                posterPath = (MoviesRepoImpl.IMAGE_BEGINNING + body.posterPath),
                voteAverage = body.voteAverage ?: 0.0,
                originalName = body.originalTitle ?: "",
                overview = body.overview ?: "",
                productionCompanies = mapToDomainModel(body.productionCompanies),
                genres = body.genres.toString(),
                releaseDate = formatDate(body.releaseDate ?: "")
            )


        private fun formatDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val outputFormat = SimpleDateFormat("dd MMM. yyyy", Locale.US)
            val date = inputFormat.parse(dateString) ?: ""
            return outputFormat.format(date)
        }

        private fun mapToDomainModel(productionCompanies: List<ProductionCompanies>): List<ProductionCompaniesDomainModel> =
            productionCompanies.map { company ->
                ProductionCompaniesDomainModel(
                    id = company.id ?: 0,
                    logoPath = (MoviesRepoImpl.IMAGE_BEGINNING + company.logoPath),
                    name = company.name ?: "",
                    originCountry = company.originCountry ?: ""
                )
            }
    }
}