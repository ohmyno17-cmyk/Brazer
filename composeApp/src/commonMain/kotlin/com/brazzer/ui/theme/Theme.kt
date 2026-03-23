package com.brazzer.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

val DarkColors =
    darkColorScheme(
        primary = md_theme_dark_primary,
        onPrimary = md_theme_dark_onPrimary,
        primaryContainer = md_theme_dark_primaryContainer,
        onPrimaryContainer = md_theme_dark_onPrimaryContainer,
        secondary = md_theme_dark_secondary,
        onSecondary = md_theme_dark_onSecondary,
        secondaryContainer = md_theme_dark_secondaryContainer,
        onSecondaryContainer = md_theme_dark_onSecondaryContainer,
        tertiary = md_theme_dark_tertiary,
        onTertiary = md_theme_dark_onTertiary,
        tertiaryContainer = md_theme_dark_tertiaryContainer,
        onTertiaryContainer = md_theme_dark_onTertiaryContainer,
        error = md_theme_dark_error,
        errorContainer = md_theme_dark_errorContainer,
        onError = md_theme_dark_onError,
        onErrorContainer = md_theme_dark_onErrorContainer,
        background = md_theme_dark_background,
        onBackground = md_theme_dark_onBackground,
        surface = md_theme_dark_surface,
        onSurface = md_theme_dark_onSurface,
        surfaceVariant = md_theme_dark_surfaceVariant,
        onSurfaceVariant = md_theme_dark_onSurfaceVariant,
        outline = md_theme_dark_outline,
        inverseOnSurface = md_theme_dark_inverseOnSurface,
        inverseSurface = md_theme_dark_inverseSurface,
        inversePrimary = md_theme_dark_inversePrimary,
        surfaceTint = md_theme_dark_surfaceTint,
        outlineVariant = md_theme_dark_outlineVariant,
        scrim = md_theme_dark_scrim,
    )

// ==========================================
// 🎨 GRADIENT BRUSHES 🎨
// ==========================================

@Composable
fun primaryGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(gradientPrimaryStart, gradientPrimaryEnd),
    start = startOffset,
    end = endOffset,
)

@Composable
fun secondaryGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(gradientSecondaryStart, gradientSecondaryEnd),
    start = startOffset,
    end = endOffset,
)

@Composable
fun accentGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(gradientAccentStart, gradientAccentEnd),
    start = startOffset,
    end = endOffset,
)

@Composable
fun fireGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(gradientFireStart, gradientFireEnd),
    start = startOffset,
    end = endOffset,
)

@Composable
fun coolGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(gradientCoolStart, gradientCoolEnd),
    start = startOffset,
    end = endOffset,
)

@Composable
fun sunsetGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(gradientSunsetStart, gradientSunsetEnd),
    start = startOffset,
    end = endOffset,
)

@Composable
fun auroraGradient(
    startOffset: Offset = Offset(0f, 0f),
    endOffset: Offset = Offset.Infinite,
): Brush = Brush.linearGradient(
    colors = listOf(
        gradientAuroraStart,
        gradientAuroraMiddle,
        gradientAuroraEnd,
    ),
    start = startOffset,
    end = endOffset,
)

// Radial Gradients
@Composable
fun radialPrimaryGradient(): Brush = Brush.radialGradient(
    colors = listOf(gradientPrimaryStart, gradientPrimaryEnd, Color.Transparent),
)

@Composable
fun radialNeonGlow(): Brush = Brush.radialGradient(
    colors = listOf(
        neonGlowPink,
        neonGlowPurple,
        Color.Transparent,
    ),
)

// ==========================================
// ✨ ANIMATED GRADIENTS ✨
// ==========================================

@Composable
fun animatedGradientBrush(
    colors: List<Color>,
    durationMillis: Int = 3000,
): Brush {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient")
    
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "offset",
    )
    
    return Brush.linearGradient(
        colors = colors,
        start = Offset(offset * 1000, 0f),
        end = Offset(1000 - offset * 1000, 1000f),
    )
}

@Composable
fun shimmerGradient(): Brush {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerOffset",
    )
    
    return Brush.linearGradient(
        colors = listOf(
            shimmerBackground,
            shimmerHighlight,
            shimmerBackground,
        ),
        start = Offset(offset * 2000, 0f),
        end = Offset(offset * 2000 + 1000, 0f),
        tileMode = TileMode.Mirror,
    )
}

// ==========================================
// 🌊 BACKGROUND MODIFIERS 🌊
// ==========================================

@Composable
fun Modifier.animatedBackground(): Modifier {
    val brush = animatedGradientBrush(
        colors = listOf(
            md_theme_dark_background,
            Color(0xFF0F0F18),
            md_theme_dark_background,
        ),
        durationMillis = 10000,
    )
    return this.then(Modifier.background(brush))
}

@Composable
fun Modifier.neonGlowBackground(): Modifier {
    val brush = radialNeonGlow()
    return this.then(Modifier.background(brush))
}

// ==========================================
// 🎭 THEME PROVIDER 🎭
// ==========================================

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppTheme(
    content:
        @Composable()
        () -> Unit,
) {
    MaterialExpressiveTheme(
        colorScheme = DarkColors,
        content = {
            CompositionLocalProvider(
                LocalContentColor provides DarkColors.onSurfaceVariant,
                content,
            )
        },
        typography = typo(),
    )
}

// ==========================================
// 🎨 GRADIENT BACKGROUND BOX 🎨
// ==========================================

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    gradientType: GradientType = GradientType.PRIMARY,
    content: @Composable () -> Unit,
) {
    val brush = when (gradientType) {
        GradientType.PRIMARY -> primaryGradient()
        GradientType.SECONDARY -> secondaryGradient()
        GradientType.ACCENT -> accentGradient()
        GradientType.FIRE -> fireGradient()
        GradientType.COOL -> coolGradient()
        GradientType.SUNSET -> sunsetGradient()
        GradientType.AURORA -> auroraGradient()
        GradientType.ANIMATED -> animatedGradientBrush(
            listOf(
                gradientPrimaryStart,
                gradientSecondaryStart,
                gradientTertiaryEnd,
                gradientPrimaryEnd,
            ),
        )
    }
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush),
    ) {
        content()
    }
}

enum class GradientType {
    PRIMARY,
    SECONDARY,
    ACCENT,
    FIRE,
    COOL,
    SUNSET,
    AURORA,
    ANIMATED,
}

// ==========================================
// 🎯 GLASSMORPHISM BOX 🎯
// ==========================================

@Composable
fun GlassBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        glassBackground,
                        glassSurface,
                    ),
                ),
            ),
    ) {
        content()
    }
}
