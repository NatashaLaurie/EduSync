package org.company.app.presentation.ui.features.saved_courses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.flow.collectLatest
import org.company.app.presentation.ui.common.CoursesList
import org.company.app.presentation.ui.common.state.ManagementResourceUiState
import org.company.app.presentation.ui.features.course_detail.CourseDetailsScreen

object SavedCoursesScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val snackbarHostState = remember { SnackbarHostState() }
        val savedСoursesViewModel = getScreenModel<SavedCoursesViewModel>()

        val state by savedСoursesViewModel.uiState.collectAsState()

        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        val navigator = LocalNavigator.current

        LaunchedEffect(key1 = Unit) {
            savedСoursesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is SavedCoursesContract.Effect.NavigateToDetailCourse ->
                        navigator?.push(CourseDetailsScreen(effect.course))

                    is SavedCoursesContract.Effect.CourseRemoved -> {
                        snackbarHostState.showSnackbar("Курс удален!")
                    }
                }
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                ActionAppBar()
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.courses,
                successView = { courses ->
                    CoursesList(
                        courses = courses,
                        onCourseClick = { course ->
                            savedСoursesViewModel.setEvent(
                                SavedCoursesContract.Event.OnCourseClick(
                                    course
                                )
                            )
                        },
                        scope = coroutineScope,
                        state = scrollState
                    )
                },
                onTryAgain = { savedСoursesViewModel.setEvent(SavedCoursesContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { savedСoursesViewModel.setEvent(SavedCoursesContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionAppBar(
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        title = {
            Text(
                textAlign = TextAlign.Center,
                text = "Мои курсы"
            )
        },
    )
}


