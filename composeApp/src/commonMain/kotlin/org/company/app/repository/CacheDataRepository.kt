package org.company.app.repository

import org.company.app.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CacheDataRepository {
    suspend fun cacheCourses(courses: List<Course>)
    suspend fun getCoursesByCategory(category: String): Flow<List<Course>>
    suspend fun getAllCourses(): Flow<List<Course>>
    suspend fun isCourseFavorite(courseId: String, state: Long): Boolean
    suspend fun updateCourseFavoriteState(courseId: String, isFavourite: Long)
    suspend fun search(query: String): Flow<List<Course>>
    suspend fun filter(
        category: String,
        paymentTerm: String,
        studentsAgeFrom: Long
    ): Flow<List<Course>>

    suspend fun getFavouriteCourses(): Flow<List<Course>>
    suspend fun getCourseById(id: String): Course
}