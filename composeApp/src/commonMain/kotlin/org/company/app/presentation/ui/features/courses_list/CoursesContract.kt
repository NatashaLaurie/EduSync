package org.company.app.presentation.ui.features.courses_list

import org.company.app.domain.model.Course
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.UiEffect
import org.company.app.presentation.mvi.UiEvent
import org.company.app.presentation.mvi.UiState

interface CoursesContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data object OnBackPressed : Event
        data class OnCourseClick(val course: Course) : Event
    }

    data class State(
        val courses: ResourceUiState<List<Course>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCourse(val course: Course) : Effect
        data object BackNavigation : Effect
    }
}


