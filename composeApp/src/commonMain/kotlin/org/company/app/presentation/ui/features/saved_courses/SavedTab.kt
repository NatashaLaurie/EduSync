package org.company.app.presentation.ui.features.saved_courses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition

object SavedTab : Tab {

    @Composable
    override fun Content() {
        Navigator(screen = SavedCoursesScreen) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Filled.Favorite)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Мои курсы",
                    icon = icon
                )
            }
        }
}