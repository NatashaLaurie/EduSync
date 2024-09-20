package org.company.app.data.remote.model

import kotlinx.serialization.Serializable
import org.company.app.data.remote.model.MapValue

@Serializable
data class Value(
    val mapValue: MapValue? = null
)