package org.company.app.presentation.ui.features.categories

import org.company.app.domain.model.Course
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.UiEffect
import org.company.app.presentation.mvi.UiEvent
import org.company.app.presentation.mvi.UiState
import org.jetbrains.compose.resources.StringResource

interface CategoriesContract {
    sealed interface Event : UiEvent {
        data class OnCategoryClick(
            val category: String,
            val stringResource: StringResource
        ) : Event

        data class OnCourseClick(val course: Course) : Event
    }

    data class State(
        val courses: ResourceUiState<List<Course>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCourse(val course: Course) : Effect
        data class NavigateToCourses(
            val category: String,
            val stringResource: StringResource
        ) : Effect
    }
}


