package com.merabk.moviesapplicationtm.domain.model

data class MainContentDomainModel(
    val id: Int,
    val overview: String,
    val name: String,
    val voteAverage: Double,
    val posterPath: String
)