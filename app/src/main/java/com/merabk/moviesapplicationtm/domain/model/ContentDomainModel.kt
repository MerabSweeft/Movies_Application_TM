package com.merabk.moviesapplicationtm.domain.model

data class ContentDomainModel(
    val id: Int,
    val originalName: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double,
)