package org.company.app.domain.use_cases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.company.app.domain.CoursesRepository
import org.company.app.domain.model.Course

class GetCoursesFavoritesUseCase(
    private val repository: CoursesRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit, List<Course>>(dispatcher) {
    override suspend fun build(param: Unit): Flow<List<Course>> =
        repository.getFavouriteCourses()
}