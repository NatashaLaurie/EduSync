package org.company.app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val groupName: String,
    val mondayLessons: String,
    val tuesdayLessons: String,
    val wednesdayLessons: String,
    val thursdayLessons: String,
    val fridayLessons: String,
    val saturdayLessons: String,
    val sundayLessons: String,
)