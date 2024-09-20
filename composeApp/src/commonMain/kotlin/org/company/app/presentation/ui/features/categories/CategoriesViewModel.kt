package org.company.app.presentation.ui.features.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import org.company.app.domain.model.Course
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.mvi.BaseViewModel

class CategoriesViewModel :
    BaseViewModel<CategoriesContract.Event, CategoriesContract.State, CategoriesContract.Effect>() {

    var searchQuery by mutableStateOf("")
    private val allCourses = mutableListOf<Course>()

    val searchResults: StateFlow<List<Course>> =
        snapshotFlow { searchQuery }
            .combine(flowOf(allCourses)) { searchQuery, courses ->
                when {
                    searchQuery.isNotEmpty() -> courses.filter { course ->
                        course.courseName.contains(searchQuery, ignoreCase = true)
                    }
                    else -> courses
                }
            }.stateIn(
                scope = screenModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    override fun createInitialState(): CategoriesContract.State =
        CategoriesContract.State(courses = ResourceUiState.Idle)

    override fun handleEvent(event: CategoriesContract.Event) {
        when (event) {
            is CategoriesContract.Event.OnCategoryClick -> setEffect {
                CategoriesContract.Effect.NavigateToCourses(
                    event.category, event.stringResource
                )
            }

            is CategoriesContract.Event.OnCourseClick -> setEffect {
                CategoriesContract.Effect.NavigateToDetailCourse(
                    event.course
                )
            }
        }
    }
}


