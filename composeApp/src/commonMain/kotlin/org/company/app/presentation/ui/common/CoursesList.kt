package org.company.app.presentation.ui.common

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.company.app.domain.model.Course

@Composable
fun CoursesList(
    courses: List<Course>,
    onCourseClick: (Course) -> Unit,
    state: LazyListState,
    scope: CoroutineScope
) {
    LazyColumn(
        state = state,
        contentPadding = PaddingValues(
            bottom = 84.dp,
        ),
        modifier = Modifier
            .testTag("courses list")
            .fillMaxSize()
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    scope.launch {
                        state.scrollBy(-delta)
                    }
                },
            ),
        verticalArrangement = Arrangement.Top
    ) {
        items(courses) { course ->
            CourseCard(
                course = course,
                onClick = { onCourseClick(course) }
            )
        }
    }
}