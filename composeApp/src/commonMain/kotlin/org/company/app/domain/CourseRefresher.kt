package org.company.app.domain

class CourseRefresher(
    private val courseRepository: CoursesRepository
) {

    suspend fun refreshCoursesOnAppOpen() {
        try {
            courseRepository.refreshCourses()
        } catch (e: Exception) {
            println("Error refreshing courses: ${e.message}")
        }
    }
}