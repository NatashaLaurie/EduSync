package org.company.app.data.remote.model

import kotlinx.serialization.Serializable
import org.company.app.data.remote.model.MapValueX

@Serializable
data class WeeklySchedule(
    val mapValue: MapValueX? = null
)