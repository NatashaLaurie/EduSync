package org.company.app.presentation.ui.features.saved_courses

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.company.app.domain.use_cases.GetCoursesFavoritesUseCase
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.BaseViewModel

class SavedCoursesViewModel(
    private val getCoursesFavoritesUseCase: GetCoursesFavoritesUseCase,
) : BaseViewModel<SavedCoursesContract.Event, SavedCoursesContract.State, SavedCoursesContract.Effect>() {

    init {
        getCSavedCourses()
    }

    override fun createInitialState(): SavedCoursesContract.State =
        SavedCoursesContract.State(courses = ResourceUiState.Idle)

    override fun handleEvent(event: SavedCoursesContract.Event) {
        when (event) {
            SavedCoursesContract.Event.OnTryCheckAgainClick -> getCSavedCourses()
            is SavedCoursesContract.Event.OnCourseClick -> setEffect {
                SavedCoursesContract.Effect.NavigateToDetailCourse(
                    event.course
                )
            }

        }
    }

    private fun getCSavedCourses() {
        setState { copy(courses = ResourceUiState.Loading) }
        screenModelScope.launch {
            getCoursesFavoritesUseCase(Unit).collect {
                it.onSuccess {
                    setState {
                        copy(
                            courses = if (it.isEmpty())
                                ResourceUiState.Empty
                            else
                                ResourceUiState.Success(it)
                        )
                    }
                }.onFailure { setState { copy(courses = ResourceUiState.Error()) } }
            }
        }
    }
}
