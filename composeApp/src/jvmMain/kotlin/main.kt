import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.company.app.App
import org.company.app.di.initKoin

fun main() = application {
    initKoin {}
    Window(
        title = "EduApp",
        state = rememberWindowState(width = 600.dp, height = 800.dp),
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}