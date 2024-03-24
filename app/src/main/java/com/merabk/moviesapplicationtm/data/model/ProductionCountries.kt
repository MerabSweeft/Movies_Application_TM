package com.merabk.moviesapplicationtm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductionCountries(

    @SerialName("iso_3166_1") val iso31661: String? = null,
    @SerialName("name") val name: String? = null

)