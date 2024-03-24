package com.merabk.moviesapplicationtm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SpokenLanguages(

    @SerialName("english_name") val englishName: String? = null,
    @SerialName("iso_639_1") val iso6391: String? = null,
    @SerialName("name") val name: String? = null

)