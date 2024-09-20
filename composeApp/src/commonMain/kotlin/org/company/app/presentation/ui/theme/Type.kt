package org.company.app.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import eduapp.composeapp.generated.resources.PoiretOne_Regular
import eduapp.composeapp.generated.resources.Res
import eduapp.composeapp.generated.resources.Ruda_Regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalResourceApi::class)
@Composable
fun getTypography(): Typography {
    val bodyFontFamily = FontFamily(
        Font(
            Res.font.Ruda_Regular, FontWeight.Normal, FontStyle.Normal
        )
    )

    val displayFontFamily = FontFamily(
        Font(
            Res.font.PoiretOne_Regular, FontWeight.Normal, FontStyle.Normal
        )
    )

    return Typography(
        displayLarge = org.company.app.theme.baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = org.company.app.theme.baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = org.company.app.theme.baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = org.company.app.theme.baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = org.company.app.theme.baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = org.company.app.theme.baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = org.company.app.theme.baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = org.company.app.theme.baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = org.company.app.theme.baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = org.company.app.theme.baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = org.company.app.theme.baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = org.company.app.theme.baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = org.company.app.theme.baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = org.company.app.theme.baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = org.company.app.theme.baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
}


// Default Material 3 typography values
val baseline = Typography()



