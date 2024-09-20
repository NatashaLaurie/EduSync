package org.company.app.data.local

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.company.app.db.CachedCourses
import org.company.app.db.Database
import org.company.app.domain.model.Course
import org.company.app.repository.CacheDataRepository

class CacheDataRepositoryImpl(
    private val database: Database
) : CacheDataRepository {

    override suspend fun cacheCourses(courses: List<Course>) {
        database {
            it.appDatabaseQueries.transaction {
                courses.forEach { course ->
                    it.appDatabaseQueries.insertCourse(
                        id = course.id,
                        category = course.category,
                        department = course.department,
                        teacherName = course.teacherName,
                        contactPhone = course.contactPhone,
                        courseName = course.courseName,
                        description = course.description,
                        imageUrl = course.imageUrl,
                        paymentTerm = course.paymentTerm,
                        studentsAgeFrom = course.studentsAgeFrom,
                        studentsAgeTo = course.studentsAgeTo,
                        schedule = course.schedule,
                        program = course.program,
                        programDuration = course.programDuration,
                        recruitingIsOpen = course.recruitingIsOpen,
                        address = course.address,
                        locationContactPhone = course.locationContactPhone,
                        roomNumber = course.roomNumber,
                        isFavorite = course.isFavourite
                    )
                }
            }
        }
    }

    override suspend fun getCoursesByCategory(category: String): Flow<List<Course>> =
        database { appDatabase ->
            appDatabase.appDatabaseQueries.selectCachedCoursesByCategory(category).asFlow().map {
                it.executeAsList()
            }.map { cachedCourses -> cachedCourses.map { it.toCourse() } }
        }

    override suspend fun getAllCourses(): Flow<List<Course>> =
        database { appDatabase ->
            appDatabase.appDatabaseQueries.selectCachedCourses().asFlow().map {
                it.executeAsList()
            }.map { cachedCourses -> cachedCourses.map { it.toCourse() } }
        }

    override suspend fun isCourseFavorite(courseId: String, state: Long): Boolean =
        database {
            it.appDatabaseQueries.selectCourseFavoriteById(courseId, 1L).executeAsOne()
        }

    override suspend fun updateCourseFavoriteState(courseId: String, isFavourite: Long) {
        database {
            it.appDatabaseQueries.updateIsFavorite(isFavourite, courseId)
        }
    }

    private fun CachedCourses.toCourse() = Course(
        id = id,
        department = department,
        category = category,
        contactPhone = contactPhone,
        description = description,
        courseName = courseName,
        imageUrl = imageUrl,
        paymentTerm = paymentTerm,
        teacherName = teacherName,
        studentsAgeFrom = studentsAgeFrom,
        studentsAgeTo = studentsAgeTo,
        schedule = schedule,
        program = program,
        programDuration = programDuration,
        recruitingIsOpen = recruitingIsOpen,
        address = address,
        locationContactPhone = locationContactPhone,
        roomNumber = roomNumber,
        isFavourite = isFavorite,
    )

    override suspend fun search(query: String): Flow<List<Course>> =
        database { appDatabase ->
            appDatabase.appDatabaseQueries.search(query).asFlow().map {
                it.executeAsList()
            }.map { cachedCourses -> cachedCourses.map { it.toCourse() } }
        }

    override suspend fun filter(
        category: String,
        paymentTerm: String,
        studentsAgeFrom: Long
    ): Flow<List<Course>> =
        database { appDatabase ->
            appDatabase.appDatabaseQueries.searchFiltered(category, paymentTerm, studentsAgeFrom)
                .asFlow().map {
                    it.executeAsList()
                }.map { cachedCourses -> cachedCourses.map { it.toCourse() } }
        }

    override suspend fun getFavouriteCourses(): Flow<List<Course>> =
        database { appDatabase ->
            appDatabase.appDatabaseQueries.selectAllFavorites().asFlow().map {
                it.executeAsList()
            }.map { cachedCourses -> cachedCourses.map { it.toCourse() } }
        }

    override suspend fun getCourseById(id: String): Course =
        database {
            it.appDatabaseQueries.selectOneById(id).executeAsOne().toCourse()
        }
}