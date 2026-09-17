package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = SproutPrimary,
  onPrimary = SproutOnPrimary,
  primaryContainer = SproutPrimaryContainer,
  onPrimaryContainer = SproutOnPrimaryContainer,
  secondary = SproutSecondary,
  onSecondary = SproutOnSecondary,
  secondaryContainer = SproutSecondaryContainer,
  onSecondaryContainer = SproutOnSecondaryContainer,
  background = SproutBackground,
  onBackground = SproutOnSurface,
  surface = SproutSurface,
  onSurface = SproutOnSurface,
  surfaceVariant = SproutSurfaceContainerHighest,
  onSurfaceVariant = SproutOnSurfaceVariant,
  surfaceContainer = SproutSurfaceContainer,
  surfaceContainerLow = SproutSurfaceContainerLow,
  surfaceContainerLowest = SproutSurfaceContainerLowest,
  surfaceContainerHigh = SproutSurfaceContainerHigh,
  surfaceContainerHighest = SproutSurfaceContainerHighest,
  outline = SproutOutline,
  outlineVariant = SproutOutlineVariant,
  inverseSurface = SproutInverseSurface,
  inverseOnSurface = SproutBackground
)

private val DarkColorScheme = darkColorScheme(
  primary = SproutPrimaryContainer,
  onPrimary = SproutOnPrimaryContainer,
  primaryContainer = SproutPrimary,
  onPrimaryContainer = SproutPrimaryFixed,
  secondary = SproutSecondaryFixed,
  onSecondary = SproutSecondary,
  secondaryContainer = SproutSecondary,
  onSecondaryContainer = SproutSecondaryContainer,
  background = Color(0xFF1B1C1A),
  onBackground = Color(0xFFE4E2DF),
  surface = Color(0xFF1B1C1A),
  onSurface = Color(0xFFE4E2DF),
  surfaceVariant = Color(0xFF30312F),
  onSurfaceVariant = Color(0xFFE0BFB7),
  surfaceContainer = Color(0xFF252624),
  surfaceContainerLow = Color(0xFF1E1F1D),
  surfaceContainerLowest = Color(0xFF141513),
  surfaceContainerHigh = Color(0xFF2C2D2A),
  surfaceContainerHighest = Color(0xFF333431),
  outline = SproutOutlineVariant,
  outlineVariant = SproutOutline,
  inverseSurface = SproutBackground,
  inverseOnSurface = SproutOnSurface
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

