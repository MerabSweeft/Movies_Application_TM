package com.merabk.moviesapplicationtm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genres(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null

)