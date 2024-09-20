package org.company.app.domain

import org.company.app.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun refreshCourses()
    suspend fun getCoursesByCategory(category: String): Flow<List<Course>>
    suspend fun getCachedCourses(): Flow<List<Course>>
    suspend fun isCourseFavorite(courseId: String): Boolean
    suspend fun getCourseById(id: String): Course
    suspend fun updateCourseFavoriteState(courseId: String, isFavourite: Long)
    suspend fun search(query: String): Flow<List<Course>>
    suspend fun filter(
        category: String,
        paymentTerm: String,
        studentsAgeFrom: Long
    ): Flow<List<Course>>

    suspend fun getFavouriteCourses(): Flow<List<Course>>
}