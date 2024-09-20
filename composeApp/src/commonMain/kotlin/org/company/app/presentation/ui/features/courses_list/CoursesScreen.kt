package org.company.app.presentation.ui.features.courses_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.flow.collectLatest
import org.company.app.presentation.ui.common.ArrowBackIcon
import org.company.app.presentation.ui.common.CoursesList
import org.company.app.presentation.ui.common.state.ManagementResourceUiState
import org.company.app.presentation.ui.features.course_detail.CourseDetailsScreen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf


class CoursesScreen(
    private val category: String,
    private val categoryStringResource: StringResource
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val snackbarHostState = remember { SnackbarHostState() }
        val coursesViewModel = getScreenModel<CoursesViewModel> { parametersOf(category) }

        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        val state by coursesViewModel.uiState.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(key1 = Unit) {
            coursesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is CoursesContract.Effect.NavigateToDetailCourse ->
                        navigator?.push(CourseDetailsScreen(effect.course))

                    is CoursesContract.Effect.BackNavigation -> navigator?.pop()
                }
            }
        }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                ActionAppBar(
                    categoryString = categoryStringResource,
                    onBackPressed = { coursesViewModel.setEvent(CoursesContract.Event.OnBackPressed) }
                )
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
                            coursesViewModel.setEvent(
                                CoursesContract.Event.OnCourseClick(
                                    course
                                )
                            )
                        },
                        scope = coroutineScope,
                        state = scrollState
                    )
                },
                onTryAgain = { coursesViewModel.setEvent(CoursesContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { coursesViewModel.setEvent(CoursesContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionAppBar(
    categoryString: StringResource,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(categoryString)) },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
    )
}




