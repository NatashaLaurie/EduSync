package org.company.app

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import eduapp.composeapp.generated.resources.Res
import eduapp.composeapp.generated.resources.decorative_creativity
import org.company.app.presentation.ui.features.courses_list.CoursesScreen
import kotlin.test.Test


@OptIn(ExperimentalTestApi::class)
class CoursesScreenTests {

    val category = "decoration_creativity"
    val title = Res.string.decorative_creativity

    private fun ComposeUiTest.launchContent() {
        setContent {
            CoursesScreen(category, title).Content()
        }
    }
    @Test
    fun TestAppBarTitleDisplayed() = runComposeUiTest {
        launchContent()
        onNodeWithText("декоративное творчество", ignoreCase = true).assertExists()
    }
}