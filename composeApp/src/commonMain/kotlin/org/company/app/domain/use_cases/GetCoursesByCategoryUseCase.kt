package org.company.app.domain.use_cases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.company.app.domain.CoursesRepository
import org.company.app.domain.model.Course

class GetCoursesByCategoryUseCase(
    private val repository: CoursesRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<String, List<Course>>(dispatcher) {
    override suspend fun build(param: String): Flow<List<Course>> =
        repository.getCoursesByCategory(param)
}