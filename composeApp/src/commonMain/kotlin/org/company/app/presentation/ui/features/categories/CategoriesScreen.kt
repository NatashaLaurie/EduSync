package org.company.app.presentation.ui.features.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import org.company.app.presentation.ui.common.Categories
import org.company.app.presentation.ui.common.CategoriesList
import org.company.app.presentation.ui.common.CoursesList
import org.company.app.presentation.ui.features.course_detail.CourseDetailsScreen
import org.company.app.presentation.ui.features.courses_list.CoursesScreen

object CategoriesScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val categoriesViewModel = getScreenModel<CategoriesViewModel>()
        val searchResults = categoriesViewModel.searchResults.collectAsState().value
        var active by remember { mutableStateOf(false) }
        val searchItems = remember { mutableStateListOf<String>() }
        val keyboardController = LocalSoftwareKeyboardController.current
        val navigator = LocalNavigator.currentOrThrow

        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(key1 = Unit) {
            categoriesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is CategoriesContract.Effect.NavigateToCourses ->
                        navigator.push(CoursesScreen(effect.category, effect.stringResource))

                    is CategoriesContract.Effect.NavigateToDetailCourse -> navigator.push(
                        CourseDetailsScreen(effect.course)
                    )
                }
            }
        }
        Scaffold {
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .testTag("categories")
            ) {
                SearchBar(
                    colors = SearchBarDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    query = categoriesViewModel.searchQuery,
                    onQueryChange = { categoriesViewModel.onSearchQueryChange(it) },
                    onSearch = {
                        searchItems.add(it)
                        keyboardController?.hide()
                    },
                    onActiveChange = { active = it },
                    placeholder = {
                        Text(text = "Поиск...")
                    },
                    content = {
                        CoursesList(
                            courses = searchResults,
                            onCourseClick = { course ->
                                categoriesViewModel.setEvent(
                                    CategoriesContract.Event.OnCourseClick(
                                        course
                                    )
                                )
                            },
                            scope = coroutineScope,
                            state = scrollState
                        )
                        searchItems.forEach {
                            Row(
                                modifier = Modifier.padding(14.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 10.dp),
                                    imageVector = Icons.Default.Refresh,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    contentDescription = "history icon"
                                )
                                Text(it)
                            }
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "search icon"
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        if (categoriesViewModel.searchQuery.isNotEmpty()) {
                                            categoriesViewModel.searchQuery = ""
                                        } else {
                                            active = false
                                        }
                                    },
                                imageVector = Icons.Default.Close,
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = "close icon"
                            )
                        }

                    },
                    active = active,
                    tonalElevation = 0.dp
                )
                CategoriesList(
                    categories = Categories.list,
                    onCategoryClick = { category ->
                        categoriesViewModel.setEvent(
                            CategoriesContract.Event.OnCategoryClick(
                                category.string, category.stringResource
                            )
                        )
                    }
                )
            }
        }
    }
}


