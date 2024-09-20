package org.company.app.presentation.ui.features.course_detail

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.company.app.domain.model.Course
import org.company.app.domain.use_cases.IsCourseFavoriteUseCase
import org.company.app.domain.use_cases.SwitchCourseFavoriteUseCase
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.BaseViewModel

class CourseDetailsViewModel(
    private val isCourseFavoriteUseCase: IsCourseFavoriteUseCase,
    private val switchCourseFavoriteUseCase: SwitchCourseFavoriteUseCase,
    private val course: Course,
) : BaseViewModel<CourseDetailsContract.Event, CourseDetailsContract.State, CourseDetailsContract.Effect>() {

    init {
        checkIfIsFavorite(course.id)
    }

    override fun createInitialState(): CourseDetailsContract.State =
        CourseDetailsContract.State(
            isFavorite = ResourceUiState.Idle,
        )

    override fun handleEvent(event: CourseDetailsContract.Event) {
        when (event) {
            CourseDetailsContract.Event.OnFavoriteClick -> switchCourseFavorite(course.id)
            CourseDetailsContract.Event.OnBackPressed -> setEffect { CourseDetailsContract.Effect.BackNavigation }
            CourseDetailsContract.Event.OnApplyClick -> setEffect { CourseDetailsContract.Effect.BottomModalSheetVisible }
        }
    }

    private fun checkIfIsFavorite(courseId: String) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        screenModelScope.launch {
            isCourseFavoriteUseCase(courseId)
                .onSuccess {
                    println("$courseId $it")
                    setState { copy(isFavorite = ResourceUiState.Success(it)) }
                }
                .onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }


    private fun switchCourseFavorite(courseId: String) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        screenModelScope.launch {
            switchCourseFavoriteUseCase(courseId)
                .onSuccess {
                    setState { copy(isFavorite = ResourceUiState.Success(it)) }
                    setEffect { CourseDetailsContract.Effect.CourseAdded }
                }.onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }
}