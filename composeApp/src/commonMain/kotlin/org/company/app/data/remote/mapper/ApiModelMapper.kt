package org.company.app.data.remote.mapper

import org.company.app.data.remote.model.Document
import org.company.app.domain.model.Course
import org.company.app.domain.model.Schedule
import org.company.app.domain.model.map.Mapper

class ApiModelMapper : Mapper<Document, Course>() {
    override fun map(model: Document): Course {

        val schedule = mutableListOf<Schedule>()

        model.fields?.groups_schedule?.arrayValue?.values?.forEach { value ->
            val groupName = value.mapValue?.fields?.group_name?.stringValue.toString()
            val weeklySchedule =
                value.mapValue?.fields?.weekly_schedule?.mapValue?.fields
            val mondayLessonsList =
                weeklySchedule?.monday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")
            val tuesdayLessonsList =
                weeklySchedule?.tuesday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")
            val wednesdayLessonsList =
                weeklySchedule?.wednesday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")
            val thursdayLessonsList =
                weeklySchedule?.thursday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")
            val fridayLessonsList =
                weeklySchedule?.friday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")
            val saturdayLessonsList =
                weeklySchedule?.saturday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")
            val sundayLessonsList =
                weeklySchedule?.sunday_lessons?.arrayValue?.values?.map {
                    it.stringValue
                }?.joinToString(separator = "+")

            schedule.add(
                Schedule(
                    groupName,
                    mondayLessonsList.toString(),
                    tuesdayLessonsList.toString(),
                    wednesdayLessonsList.toString(),
                    thursdayLessonsList.toString(),
                    fridayLessonsList.toString(),
                    saturdayLessonsList.toString(),
                    sundayLessonsList.toString()
                )
            )
        }
        return Course(
            id = model.fields?.id?.stringValue.toString(),
            category = model.fields?.category?.stringValue.toString(),
            department = model.fields?.department?.stringValue.toString(),
            locationContactPhone = model.fields?.location_info?.mapValue?.fields?.contact_phone?.stringValue.toString(),
            address = model.fields?.location_info?.mapValue?.fields?.address?.stringValue.toString(),
            roomNumber = model.fields?.location_info?.mapValue?.fields?.room_number?.stringValue.toString(),
            contactPhone = model.fields?.contact_phone?.stringValue.toString(),
            courseName = model.fields?.name?.stringValue.toString(),
            description = model.fields?.description?.stringValue.toString(),
            imageUrl = model.fields?.image_url?.stringValue.toString(),
            paymentTerm = model.fields?.payment_term?.stringValue.toString(),
            teacherName = model.fields?.teacher_name?.stringValue.toString(),
            studentsAgeFrom = model.fields?.student_age_from?.integerValue.toString().toLong(),
            studentsAgeTo = model.fields?.student_age_to?.integerValue.toString().toLong(),
            schedule = schedule,
            program = model.fields?.program?.stringValue.toString(),
            programDuration = model.fields?.program_duration?.stringValue.toString(),
            recruitingIsOpen = if (model.fields?.recruiting_is_open?.booleanValue == true) 1 else 0,
            isFavourite = 0
        )
    }
}