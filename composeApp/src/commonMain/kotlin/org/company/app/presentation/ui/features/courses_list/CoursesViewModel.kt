package org.company.app.presentation.ui.features.courses_list

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.company.app.domain.use_cases.GetCoursesByCategoryUseCase
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.BaseViewModel

class CoursesViewModel(
    private val getCoursesUseCase: GetCoursesByCategoryUseCase,
    private val category: String
) : BaseViewModel<CoursesContract.Event, CoursesContract.State, CoursesContract.Effect>() {

    init {
        getCourses(category)
    }

    override fun createInitialState(): CoursesContract.State =
        CoursesContract.State(courses = ResourceUiState.Idle)

    override fun handleEvent(event: CoursesContract.Event) {
        when (event) {
            is CoursesContract.Event.OnTryCheckAgainClick -> getCourses(category)
            is CoursesContract.Event.OnCourseClick -> setEffect {
                CoursesContract.Effect.NavigateToDetailCourse(
                    event.course
                )
            }
            is CoursesContract.Event.OnBackPressed -> setEffect { CoursesContract.Effect.BackNavigation }
        }
    }

    private fun getCourses(category: String) {
        setState { copy(courses = ResourceUiState.Loading) }
        screenModelScope.launch {
            getCoursesUseCase(category).collect {
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
