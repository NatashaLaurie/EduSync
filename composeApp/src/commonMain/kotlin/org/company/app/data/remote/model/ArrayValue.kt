package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ArrayValue(
    val values: List<Value>? = listOf()
)