package org.company.app.presentation.ui.features.course_detail

import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.UiEffect
import org.company.app.presentation.mvi.UiEvent
import org.company.app.presentation.mvi.UiState

interface CourseDetailsContract {
    sealed interface Event : UiEvent {
        data object OnFavoriteClick : Event
        data object OnBackPressed : Event
        data object OnApplyClick : Event
    }

    data class State(
        val isFavorite: ResourceUiState<Boolean>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object CourseAdded : Effect
        data object CourseRemoved : Effect
        data object BackNavigation : Effect
        data object BottomModalSheetVisible : Effect
    }
}