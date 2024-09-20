package org.company.app.data.remote.model

import kotlinx.serialization.Serializable
import org.company.app.data.remote.model.ArrayValue

@Serializable
data class GroupsSchedule(
    val arrayValue: ArrayValue? = null
)