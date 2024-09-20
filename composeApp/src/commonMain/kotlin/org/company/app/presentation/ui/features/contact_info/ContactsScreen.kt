package org.company.app.presentation.ui.features.contact_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import eduapp.composeapp.generated.resources.Res
import eduapp.composeapp.generated.resources.baseline_language_24
import eduapp.composeapp.generated.resources.icons8_instagram
import eduapp.composeapp.generated.resources.icons8_youtube
import org.company.app.makeCall
import org.company.app.openMap
import org.company.app.openUrl
import org.jetbrains.compose.resources.painterResource

object ContactsScreen : Tab {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                ActionAppBar()
            },
            content = {
                ContactUsScreen(
                    "+375 17 360 03 35",
                    "53.88062549446942, 27.538570308877663",
                    "http://oktcvr.minsk.edu.by/",
                    "https://www.youtube.com/channel/UCELloWoD76hMPixNeF_YiCw/featured",
                    "https://www.instagram.com/vetraz_official"
                )
            }
        )

    }

    override val options: TabOptions
        @Composable get() {
            val icon = rememberVectorPainter(Icons.Filled.Info)
            return remember {
                TabOptions(
                    index = 1u, title = "Контакты", icon = icon
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
                text = "Контакты"
            )
        },
    )
}

@Composable
fun ContactUsScreen(
    number: String, coords: String, webUrl: String, youtube: String, instagram: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(
                top = 72.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 64.dp
            )
    ) {
        Text(
            text = "Свяжитесь с нами",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(modifier = Modifier.clickable {
            makeCall(number)
        }.padding(bottom = 16.dp)) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Телефон: +375 17 234 54 65"
            )
        }
        // Location
        Row(modifier = Modifier.clickable {
            openMap(coords)
        }.padding(bottom = 16.dp)) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Адрес главного офиса : г.Минск, ул.Чкалова, 1/4",
            )
        }
        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
        // Social Media
        Text(
            text = "Следите за нами:",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // web
        Row(modifier = Modifier.clickable {
            openUrl(webUrl)
        }.padding(bottom = 8.dp)) {
            Icon(
                painter = painterResource(Res.drawable.baseline_language_24),
                contentDescription = "web Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Сайт",
            )
        }

        // youtube
        Row(modifier = Modifier.clickable {
            openUrl(youtube)
        }.padding(bottom = 8.dp)) {
            Icon(
                painter = painterResource(Res.drawable.icons8_youtube),
                contentDescription = "youtube Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Youtube",
            )
        }

        // Instagram
        Row(modifier = Modifier.clickable {
            openUrl(instagram)
        }.padding(bottom = 8.dp)) {
            Icon(
                painter = painterResource(Res.drawable.icons8_instagram),
                contentDescription = "Instagram Icon", modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Instagram",
            )
        }
    }
}



