package org.example.compose.kmm.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import org.company.app.theme.ColorFamily

private val lightScheme = lightColorScheme(
    primary = org.company.app.theme.primaryLight,
    onPrimary = org.company.app.theme.onPrimaryLight,
    primaryContainer = org.company.app.theme.primaryContainerLight,
    onPrimaryContainer = org.company.app.theme.onPrimaryContainerLight,
    secondary = org.company.app.theme.secondaryLight,
    onSecondary = org.company.app.theme.onSecondaryLight,
    secondaryContainer = org.company.app.theme.secondaryContainerLight,
    onSecondaryContainer = org.company.app.theme.onSecondaryContainerLight,
    tertiary = org.company.app.theme.tertiaryLight,
    onTertiary = org.company.app.theme.onTertiaryLight,
    tertiaryContainer = org.company.app.theme.tertiaryContainerLight,
    onTertiaryContainer = org.company.app.theme.onTertiaryContainerLight,
    error = org.company.app.theme.errorLight,
    onError = org.company.app.theme.onErrorLight,
    errorContainer = org.company.app.theme.errorContainerLight,
    onErrorContainer = org.company.app.theme.onErrorContainerLight,
    background = org.company.app.theme.backgroundLight,
    onBackground = org.company.app.theme.onBackgroundLight,
    surface = org.company.app.theme.surfaceLight,
    onSurface = org.company.app.theme.onSurfaceLight,
    surfaceVariant = org.company.app.theme.surfaceVariantLight,
    onSurfaceVariant = org.company.app.theme.onSurfaceVariantLight,
    outline = org.company.app.theme.outlineLight,
    outlineVariant = org.company.app.theme.outlineVariantLight,
    scrim = org.company.app.theme.scrimLight,
    inverseSurface = org.company.app.theme.inverseSurfaceLight,
    inverseOnSurface = org.company.app.theme.inverseOnSurfaceLight,
    inversePrimary = org.company.app.theme.inversePrimaryLight,
    surfaceDim = org.company.app.theme.surfaceDimLight,
    surfaceBright = org.company.app.theme.surfaceBrightLight,
    surfaceContainerLowest = org.company.app.theme.surfaceContainerLowestLight,
    surfaceContainerLow = org.company.app.theme.surfaceContainerLowLight,
    surfaceContainer = org.company.app.theme.surfaceContainerLight,
    surfaceContainerHigh = org.company.app.theme.surfaceContainerHighLight,
    surfaceContainerHighest = org.company.app.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = org.company.app.theme.primaryDark,
    onPrimary = org.company.app.theme.onPrimaryDark,
    primaryContainer = org.company.app.theme.primaryContainerDark,
    onPrimaryContainer = org.company.app.theme.onPrimaryContainerDark,
    secondary = org.company.app.theme.secondaryDark,
    onSecondary = org.company.app.theme.onSecondaryDark,
    secondaryContainer = org.company.app.theme.secondaryContainerDark,
    onSecondaryContainer = org.company.app.theme.onSecondaryContainerDark,
    tertiary = org.company.app.theme.tertiaryDark,
    onTertiary = org.company.app.theme.onTertiaryDark,
    tertiaryContainer = org.company.app.theme.tertiaryContainerDark,
    onTertiaryContainer = org.company.app.theme.onTertiaryContainerDark,
    error = org.company.app.theme.errorDark,
    onError = org.company.app.theme.onErrorDark,
    errorContainer = org.company.app.theme.errorContainerDark,
    onErrorContainer = org.company.app.theme.onErrorContainerDark,
    background = org.company.app.theme.backgroundDark,
    onBackground = org.company.app.theme.onBackgroundDark,
    surface = org.company.app.theme.surfaceDark,
    onSurface = org.company.app.theme.onSurfaceDark,
    surfaceVariant = org.company.app.theme.surfaceVariantDark,
    onSurfaceVariant = org.company.app.theme.onSurfaceVariantDark,
    outline = org.company.app.theme.outlineDark,
    outlineVariant = org.company.app.theme.outlineVariantDark,
    scrim = org.company.app.theme.scrimDark,
    inverseSurface = org.company.app.theme.inverseSurfaceDark,
    inverseOnSurface = org.company.app.theme.inverseOnSurfaceDark,
    inversePrimary = org.company.app.theme.inversePrimaryDark,
    surfaceDim = org.company.app.theme.surfaceDimDark,
    surfaceBright = org.company.app.theme.surfaceBrightDark,
    surfaceContainerLowest = org.company.app.theme.surfaceContainerLowestDark,
    surfaceContainerLow = org.company.app.theme.surfaceContainerLowDark,
    surfaceContainer = org.company.app.theme.surfaceContainerDark,
    surfaceContainerHigh = org.company.app.theme.surfaceContainerHighDark,
    surfaceContainerHighest = org.company.app.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = org.company.app.theme.primaryLightMediumContrast,
    onPrimary = org.company.app.theme.onPrimaryLightMediumContrast,
    primaryContainer = org.company.app.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = org.company.app.theme.onPrimaryContainerLightMediumContrast,
    secondary = org.company.app.theme.secondaryLightMediumContrast,
    onSecondary = org.company.app.theme.onSecondaryLightMediumContrast,
    secondaryContainer = org.company.app.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = org.company.app.theme.onSecondaryContainerLightMediumContrast,
    tertiary = org.company.app.theme.tertiaryLightMediumContrast,
    onTertiary = org.company.app.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = org.company.app.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = org.company.app.theme.onTertiaryContainerLightMediumContrast,
    error = org.company.app.theme.errorLightMediumContrast,
    onError = org.company.app.theme.onErrorLightMediumContrast,
    errorContainer = org.company.app.theme.errorContainerLightMediumContrast,
    onErrorContainer = org.company.app.theme.onErrorContainerLightMediumContrast,
    background = org.company.app.theme.backgroundLightMediumContrast,
    onBackground = org.company.app.theme.onBackgroundLightMediumContrast,
    surface = org.company.app.theme.surfaceLightMediumContrast,
    onSurface = org.company.app.theme.onSurfaceLightMediumContrast,
    surfaceVariant = org.company.app.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = org.company.app.theme.onSurfaceVariantLightMediumContrast,
    outline = org.company.app.theme.outlineLightMediumContrast,
    outlineVariant = org.company.app.theme.outlineVariantLightMediumContrast,
    scrim = org.company.app.theme.scrimLightMediumContrast,
    inverseSurface = org.company.app.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = org.company.app.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = org.company.app.theme.inversePrimaryLightMediumContrast,
    surfaceDim = org.company.app.theme.surfaceDimLightMediumContrast,
    surfaceBright = org.company.app.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = org.company.app.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = org.company.app.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = org.company.app.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = org.company.app.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = org.company.app.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = org.company.app.theme.primaryLightHighContrast,
    onPrimary = org.company.app.theme.onPrimaryLightHighContrast,
    primaryContainer = org.company.app.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = org.company.app.theme.onPrimaryContainerLightHighContrast,
    secondary = org.company.app.theme.secondaryLightHighContrast,
    onSecondary = org.company.app.theme.onSecondaryLightHighContrast,
    secondaryContainer = org.company.app.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = org.company.app.theme.onSecondaryContainerLightHighContrast,
    tertiary = org.company.app.theme.tertiaryLightHighContrast,
    onTertiary = org.company.app.theme.onTertiaryLightHighContrast,
    tertiaryContainer = org.company.app.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = org.company.app.theme.onTertiaryContainerLightHighContrast,
    error = org.company.app.theme.errorLightHighContrast,
    onError = org.company.app.theme.onErrorLightHighContrast,
    errorContainer = org.company.app.theme.errorContainerLightHighContrast,
    onErrorContainer = org.company.app.theme.onErrorContainerLightHighContrast,
    background = org.company.app.theme.backgroundLightHighContrast,
    onBackground = org.company.app.theme.onBackgroundLightHighContrast,
    surface = org.company.app.theme.surfaceLightHighContrast,
    onSurface = org.company.app.theme.onSurfaceLightHighContrast,
    surfaceVariant = org.company.app.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = org.company.app.theme.onSurfaceVariantLightHighContrast,
    outline = org.company.app.theme.outlineLightHighContrast,
    outlineVariant = org.company.app.theme.outlineVariantLightHighContrast,
    scrim = org.company.app.theme.scrimLightHighContrast,
    inverseSurface = org.company.app.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = org.company.app.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = org.company.app.theme.inversePrimaryLightHighContrast,
    surfaceDim = org.company.app.theme.surfaceDimLightHighContrast,
    surfaceBright = org.company.app.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = org.company.app.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = org.company.app.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = org.company.app.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = org.company.app.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = org.company.app.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = org.company.app.theme.primaryDarkMediumContrast,
    onPrimary = org.company.app.theme.onPrimaryDarkMediumContrast,
    primaryContainer = org.company.app.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = org.company.app.theme.onPrimaryContainerDarkMediumContrast,
    secondary = org.company.app.theme.secondaryDarkMediumContrast,
    onSecondary = org.company.app.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = org.company.app.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = org.company.app.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = org.company.app.theme.tertiaryDarkMediumContrast,
    onTertiary = org.company.app.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = org.company.app.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = org.company.app.theme.onTertiaryContainerDarkMediumContrast,
    error = org.company.app.theme.errorDarkMediumContrast,
    onError = org.company.app.theme.onErrorDarkMediumContrast,
    errorContainer = org.company.app.theme.errorContainerDarkMediumContrast,
    onErrorContainer = org.company.app.theme.onErrorContainerDarkMediumContrast,
    background = org.company.app.theme.backgroundDarkMediumContrast,
    onBackground = org.company.app.theme.onBackgroundDarkMediumContrast,
    surface = org.company.app.theme.surfaceDarkMediumContrast,
    onSurface = org.company.app.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = org.company.app.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = org.company.app.theme.onSurfaceVariantDarkMediumContrast,
    outline = org.company.app.theme.outlineDarkMediumContrast,
    outlineVariant = org.company.app.theme.outlineVariantDarkMediumContrast,
    scrim = org.company.app.theme.scrimDarkMediumContrast,
    inverseSurface = org.company.app.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = org.company.app.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = org.company.app.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = org.company.app.theme.surfaceDimDarkMediumContrast,
    surfaceBright = org.company.app.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = org.company.app.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = org.company.app.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = org.company.app.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = org.company.app.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = org.company.app.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = org.company.app.theme.primaryDarkHighContrast,
    onPrimary = org.company.app.theme.onPrimaryDarkHighContrast,
    primaryContainer = org.company.app.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = org.company.app.theme.onPrimaryContainerDarkHighContrast,
    secondary = org.company.app.theme.secondaryDarkHighContrast,
    onSecondary = org.company.app.theme.onSecondaryDarkHighContrast,
    secondaryContainer = org.company.app.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = org.company.app.theme.onSecondaryContainerDarkHighContrast,
    tertiary = org.company.app.theme.tertiaryDarkHighContrast,
    onTertiary = org.company.app.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = org.company.app.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = org.company.app.theme.onTertiaryContainerDarkHighContrast,
    error = org.company.app.theme.errorDarkHighContrast,
    onError = org.company.app.theme.onErrorDarkHighContrast,
    errorContainer = org.company.app.theme.errorContainerDarkHighContrast,
    onErrorContainer = org.company.app.theme.onErrorContainerDarkHighContrast,
    background = org.company.app.theme.backgroundDarkHighContrast,
    onBackground = org.company.app.theme.onBackgroundDarkHighContrast,
    surface = org.company.app.theme.surfaceDarkHighContrast,
    onSurface = org.company.app.theme.onSurfaceDarkHighContrast,
    surfaceVariant = org.company.app.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = org.company.app.theme.onSurfaceVariantDarkHighContrast,
    outline = org.company.app.theme.outlineDarkHighContrast,
    outlineVariant = org.company.app.theme.outlineVariantDarkHighContrast,
    scrim = org.company.app.theme.scrimDarkHighContrast,
    inverseSurface = org.company.app.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = org.company.app.theme.inverseOnSurfaceDarkHighContrast,
    inversePrimary = org.company.app.theme.inversePrimaryDarkHighContrast,
    surfaceDim = org.company.app.theme.surfaceDimDarkHighContrast,
    surfaceBright = org.company.app.theme.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = org.company.app.theme.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = org.company.app.theme.surfaceContainerLowDarkHighContrast,
    surfaceContainer = org.company.app.theme.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = org.company.app.theme.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = org.company.app.theme.surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        darkTheme -> highContrastDarkColorScheme
        else -> mediumContrastLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = org.company.app.theme.getTypography(),
        content = content
    )
}

