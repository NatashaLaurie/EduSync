package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class FieldsX(
    val group_name: GroupName? = null,
    val weekly_schedule: WeeklySchedule? = null
)