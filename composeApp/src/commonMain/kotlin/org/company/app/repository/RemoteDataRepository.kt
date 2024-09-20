package org.company.app.repository

import org.company.app.domain.model.Course

interface RemoteDataRepository {
    suspend fun getCoursesFromApiByCategory(category: String): List<Course>
}