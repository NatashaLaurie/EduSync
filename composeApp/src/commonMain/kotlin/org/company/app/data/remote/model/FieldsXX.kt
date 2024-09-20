package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class FieldsXX(
    val friday_lessons: FridayLessons? = null,
    val monday_lessons: MondayLessons? = null,
    val saturday_lessons: SaturdayLessons? = null,
    val sunday_lessons: SundayLessons? = null,
    val thursday_lessons: ThursdayLessons? = null,
    val tuesday_lessons: TuesdayLessons? = null,
    val wednesday_lessons: WednesdayLessons? = null
)