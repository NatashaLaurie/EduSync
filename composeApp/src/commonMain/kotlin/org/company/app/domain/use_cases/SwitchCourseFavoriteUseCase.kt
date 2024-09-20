package org.company.app.domain.use_cases

import kotlinx.coroutines.CoroutineDispatcher
import org.company.app.domain.CoursesRepository

class SwitchCourseFavoriteUseCase(
    private val repository: CoursesRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<String, Boolean>(dispatcher) {
    override suspend fun block(param: String): Boolean {
        if (repository.isCourseFavorite(param)) {
            repository.updateCourseFavoriteState(param, 0L)
        } else {
            repository.updateCourseFavoriteState(param, 1L)
        }
        return repository.isCourseFavorite(param)
    }
}