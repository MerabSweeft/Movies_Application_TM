package com.merabk.moviesapplicationtm.domain.model


data class DetailsContentDomainModel(
    val id: Int,
    val originalName: String,
    val overview: String,
    val posterPath: String,
    val productionCompanies: List<ProductionCompaniesDomainModel>,
    val genres: String,
    val releaseDate: String,
    val voteAverage: Double,
)