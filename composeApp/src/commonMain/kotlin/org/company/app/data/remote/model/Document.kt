package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Document(
    val createTime: String? = null,
    val fields: Fields? = null,
    val name: String? = null,
    val updateTime: String? = null
)