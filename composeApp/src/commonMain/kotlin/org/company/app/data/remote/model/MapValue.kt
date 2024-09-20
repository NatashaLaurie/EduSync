package org.company.app.data.remote.model

import kotlinx.serialization.Serializable
import org.company.app.data.remote.model.FieldsX

@Serializable
data class MapValue(
    val fields: FieldsX? = null
)