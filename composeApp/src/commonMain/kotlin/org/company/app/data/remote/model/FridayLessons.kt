package org.company.app.data.remote.model

import kotlinx.serialization.Serializable
import org.company.app.data.remote.model.ArrayValueX

@Serializable
data class FridayLessons(
    val arrayValue: ArrayValueX? = null
)