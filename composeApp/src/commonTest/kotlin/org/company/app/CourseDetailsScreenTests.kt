package org.company.app

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import org.company.app.domain.model.Course
import org.company.app.domain.model.Schedule
import org.company.app.presentation.ui.features.course_detail.CourseDetailsScreen

@OptIn(ExperimentalTestApi::class)
class CourseDetailsScreenTests {

    val course = Course(
        "1",
        "Отделение экологической работы",
        "Декоративное творчество",
        "375 29 333 44 55",
        "Отделение экологической работы",
        "Квиллинг",
        "https://firebasestorage.googleapis.com/v0/b/vetraz-f48ba.appspot.com/o/sewing-tools.jpg?alt=media&token=95ca6907-b713-477f-80ed-24684eed0042",
        "Платно",
        "Иванова Надежда",
        4,
        8,
        listOf(Schedule("группа 1", "", "8:00-9:00", "", "8:00-9:00", "", "8:00-9:00", "")),
        "Базовая программа",
        "2 года",
        1L,
        "Независимости 34-а",
        "8 017 345 54 54",
        "235а",
        0L
    )

    private fun ComposeUiTest.launchContent() {
        setContent {
            CourseDetailsScreen(course).Content()
        }
    }

//    @Test
//    fun TestCoursesList() = runComposeUiTest {
//        launchContent()
//        onNodeWithTag("back").performClick()
//        onNodeWithTag("categories").assertExists()
//
//    }
}