package dev.pace.businesscard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ShadowBlue,
    background = NavyBlue,
    surface = SteelBlue,
    onPrimary = Platinum,
    onBackground = Platinum,
    onSurface = Platinum
)

private val LightColorScheme = lightColorScheme(
    primary = ShadowBlue,
    background = Platinum,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = NavyBlue,
    onSurface = NavyBlue
)

@Composable
fun BusinessCardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
