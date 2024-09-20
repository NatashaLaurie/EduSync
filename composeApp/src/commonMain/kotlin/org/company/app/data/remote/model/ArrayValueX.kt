package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ArrayValueX(
    val values: List<ValueX>? = listOf()
)