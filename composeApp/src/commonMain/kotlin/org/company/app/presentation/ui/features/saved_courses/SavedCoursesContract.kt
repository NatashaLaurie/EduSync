package org.company.app.presentation.ui.features.saved_courses

import org.company.app.domain.model.Course
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.UiEffect
import org.company.app.presentation.mvi.UiEvent
import org.company.app.presentation.mvi.UiState

interface SavedCoursesContract {
    sealed interface Event : UiEvent {
        data object OnTryCheckAgainClick : Event
        data class OnCourseClick(val course: Course) : Event
    }

    data class State(
        val courses: ResourceUiState<List<Course>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCourse(val course: Course) : Effect
        data object CourseRemoved : Effect

    }
}


