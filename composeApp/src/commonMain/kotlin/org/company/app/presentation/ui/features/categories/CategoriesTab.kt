package org.company.app.presentation.ui.features.categories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.company.app.presentation.ui.features.categories.CategoriesScreen

object CategoriesTab: Tab {

    @Composable
    override fun Content() {
        Navigator(screen = CategoriesScreen) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Filled.Menu)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Категории",
                    icon = icon
                )
            }
        }
}