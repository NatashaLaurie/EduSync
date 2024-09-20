package org.company.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Fields(
    val contact_phone: ContactPhone? = null,
    val department: Department? = null,
    val description: Description? = null,
    val category: Category? = null,
    val groups_schedule: GroupsSchedule? = null,
    val id: Id? = null,
    val image_url: ImageUrl? = null,
    val location_info: LocationInfo? = null,
    val name: Name? = null,
    val payment_term: PaymentTerm? = null,
    val program: Program? = null,
    val program_duration: ProgramDuration? = null,
    val recruiting_is_open: RecruitingIsOpen? = null,
    val student_age_from: StudentAgeFrom? = null,
    val student_age_to: StudentAgeTo? = null,
    val teacher_name: TeacherName? = null
)