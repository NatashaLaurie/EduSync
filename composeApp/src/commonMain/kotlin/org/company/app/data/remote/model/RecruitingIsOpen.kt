package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RecruitingIsOpen(
    val booleanValue: Boolean? = null
)