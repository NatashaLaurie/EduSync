package org.company.app.domain.use_cases

import kotlinx.coroutines.CoroutineDispatcher
import org.company.app.domain.CoursesRepository

class IsCourseFavoriteUseCase(
    private val repository: CoursesRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<String, Boolean>(dispatcher) {
    override suspend fun block(param: String): Boolean = repository.isCourseFavorite(param)
}