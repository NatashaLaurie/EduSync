package org.company.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.company.app.di.initKoin
import org.company.app.domain.CourseRefresher
import org.company.app.presentation.ui.features.categories.CategoriesTab
import org.company.app.presentation.ui.features.contact_info.ContactsScreen
import org.company.app.presentation.ui.features.saved_courses.SavedTab
import org.company.app.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import org.koin.core.Koin

@OptIn(DelicateCoroutinesApi::class)
@Composable
@Preview
fun App(
) = AppTheme() {
    BottomNavigation()
    val courseRefresher: CourseRefresher = getKoin().get()
    GlobalScope.launch {
        courseRefresher.refreshCoursesOnAppOpen()
    }
}

@Composable
private fun BottomNavigation() {
    TabNavigator(CategoriesTab) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    TabNavigationItem(CategoriesTab)
                    TabNavigationItem(SavedTab)
                    TabNavigationItem(ContactsScreen)
                }
            }
        ) {
            CurrentScreen()
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        alwaysShowLabel = true,
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title) },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title
                )
            }
        }

    )
}

