package org.company.app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: String,
    val department: String,
    val category: String,
    val contactPhone: String,
    val description: String,
    val courseName: String,
    val imageUrl: String,
    val paymentTerm: String,
    val teacherName: String,
    val studentsAgeFrom: Long,
    val studentsAgeTo: Long,
    val schedule: List<Schedule>,
    val program: String,
    val programDuration: String,
    val recruitingIsOpen: Long,
    val address: String,
    val locationContactPhone: String,
    val roomNumber: String,
    val isFavourite: Long,
)

