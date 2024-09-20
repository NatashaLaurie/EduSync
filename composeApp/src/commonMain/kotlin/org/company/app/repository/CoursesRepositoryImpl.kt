package org.company.app.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.company.app.domain.CoursesRepository
import org.company.app.domain.model.Course

class CoursesRepositoryImpl(
    private val cacheDataRepository: CacheDataRepository,
    private val remoteDataRepository: RemoteDataRepository
) : CoursesRepository {

    private suspend fun cacheCourses(courses: List<Course>) {
        cacheDataRepository.cacheCourses(courses)
    }

    override suspend fun getCoursesByCategory(category: String): Flow<List<Course>> =
        cacheDataRepository.getCoursesByCategory(category)

    override suspend fun getCachedCourses(): Flow<List<Course>> =
        cacheDataRepository.getAllCourses()

    override suspend fun isCourseFavorite(courseId: String): Boolean =
        cacheDataRepository.isCourseFavorite(courseId, 0L)

    private suspend fun getAllCoursesFromApi(): Flow<List<Course>> = flow {
        coroutineScope {
            val categories = listOf(
                Categories.DECOR,
                Categories.ART,
                Categories.CHOREOGRAPHIC,
                Categories.PRESCHOOLERS,
                Categories.ECOLOGY,
                Categories.LANG,
                Categories.INTELLECT,
                Categories.IT,
                Categories.MUSIC,
                Categories.SIGNING,
                Categories.SPORT,
                Categories.TECHNICAL
            )

            val deferredCourses = categories.map { category ->
                async { remoteDataRepository.getCoursesFromApiByCategory(category) }
            }

            val allCourses = deferredCourses.awaitAll().flatten()
            emit(allCourses)
        }
    }

    override suspend fun refreshCourses() {
        try {
            getAllCoursesFromApi().collect { allCourses ->
                cacheCourses(allCourses)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getCourseById(id: String): Course =
        cacheDataRepository.getCourseById(id)


    override suspend fun updateCourseFavoriteState(courseId: String, isFavourite: Long) {
        cacheDataRepository.updateCourseFavoriteState(courseId, isFavourite)
    }

    override suspend fun search(query: String): Flow<List<Course>> =
        cacheDataRepository.search(query)


    override suspend fun filter(
        category: String,
        paymentTerm: String,
        studentsAgeFrom: Long
    ): Flow<List<Course>> =
        cacheDataRepository.filter(category, paymentTerm, studentsAgeFrom)


    override suspend fun getFavouriteCourses(): Flow<List<Course>> =
        cacheDataRepository.getFavouriteCourses()

}