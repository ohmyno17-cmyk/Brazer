package com.brazzer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import brazzer.composeapp.generated.resources.Res
import brazzer.composeapp.generated.resources.poppins_medium
import brazzer.composeapp.generated.resources.poppins_bold
import brazzer.composeapp.generated.resources.poppins_regular

@Composable
fun fontFamily(): FontFamily =
    FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal, FontStyle.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium, FontStyle.Normal),
        Font(Res.font.poppins_bold, FontWeight.Bold, FontStyle.Normal),
    )

@Composable
fun typo(): Typography {
    val fontFamily = fontFamily()

    val typo =
        Typography(
            // ==========================================
            // 📝 TITLE STYLES 📝
            // ==========================================
            
            /***
             * Small titles - Used for card titles, list items
             */
            titleSmall =
                TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.5.sp,
                ),
            
            /***
             * Medium titles - Used for section headers, playlist names
             */
            titleMedium =
                TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.3.sp,
                ),
            
            /***
             * Large titles - Used for main headers, now playing
             */
            titleLarge =
                TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.sp,
                ),

            // ==========================================
            // 📄 BODY STYLES 📄
            // ==========================================
            
            /***
             * Small body text - Used for subtitles, metadata
             */
            bodySmall =
                TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Color(0xFFB8B8C8),
                    letterSpacing = 0.3.sp,
                ),
            
            /***
             * Medium body text - Used for descriptions, artist names
             */
            bodyMedium =
                TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Color(0xFFB8B8C8),
                    letterSpacing = 0.2.sp,
                ),
            
            /***
             * Large body text - Used for main content
             */
            bodyLarge =
                TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Color(0xFFB8B8C8),
                    letterSpacing = 0.1.sp,
                ),

            // ==========================================
            // 🖼️ DISPLAY STYLES 🖼️
            // ==========================================
            
            /***
             * Display large - Used for splash screens, big announcements
             */
            displayLarge =
                TextStyle(
                    fontSize = 57.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = (-0.25).sp,
                ),
            
            /***
             * Display medium - Used for featured content
             */
            displayMedium =
                TextStyle(
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.sp,
                ),
            
            /***
             * Display small - Used for album art overlays
             */
            displaySmall =
                TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.sp,
                ),

            // ==========================================
            // 📰 HEADLINE STYLES 📰
            // ==========================================
            
            /***
             * Headline large - Used for playlist headers
             */
            headlineLarge =
                TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.sp,
                ),
            
            /***
             * Headline medium - Used for section headers
             */
            headlineMedium =
                TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.sp,
                ),
            
            /***
             * Headline small - Used for card headers
             */
            headlineSmall =
                TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.sp,
                ),

            // ==========================================
            // 🏷️ LABEL STYLES 🏷️
            // ==========================================
            
            /***
             * Label large - Used for button text
             */
            labelLarge =
                TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    letterSpacing = 0.1.sp,
                ),
            
            /***
             * Label medium - Used for tags, chips
             */
            labelMedium =
                TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color(0xFFB8B8C8),
                    letterSpacing = 0.5.sp,
                ),
            
            /***
             * Label small - Used for timestamps, small badges
             */
            labelSmall =
                TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fontFamily,
                    color = Color(0xFFB8B8C8),
                    letterSpacing = 0.5.sp,
                ),
        )
    return typo
}

// ==========================================
// 🌈 SPECIAL TEXT STYLES 🌈
// ==========================================

/**
 * Gradient text style for special headers
 */
@Composable
fun gradientTextStyle(
    fontSize: androidx.compose.ui.unit.TextUnit = 28.sp,
    fontWeight: FontWeight = FontWeight.Bold,
): TextStyle = TextStyle(
    fontSize = fontSize,
    fontWeight = fontWeight,
    fontFamily = fontFamily(),
    brush = primaryGradient(),
)

/**
 * Neon glow text style
 */
@Composable
fun neonTextStyle(
    fontSize: androidx.compose.ui.unit.TextUnit = 28.sp,
    glowColor: Color = neonGlowPink,
): TextStyle = TextStyle(
    fontSize = fontSize,
    fontWeight = FontWeight.Bold,
    fontFamily = fontFamily(),
    color = Color.White,
    shadow = androidx.compose.ui.graphics.Shadow(
        color = glowColor,
        blurRadius = 20f,
        offset = androidx.compose.ui.geometry.Offset(0f, 0f),
    ),
)

/**
 * Brand title style for brazzer logo
 */
@Composable
fun brandTitleStyle(): TextStyle = TextStyle(
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = fontFamily(),
    brush = Brush.linearGradient(
        colors = listOf(brazzerPrimary, brazzerSecondary),
    ),
    letterSpacing = 2.sp,
)

/**
 * Now playing song title style
 */
@Composable
fun songTitleStyle(): TextStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = fontFamily(),
    color = Color.White,
)

/**
 * Now playing artist style
 */
@Composable
fun artistStyle(): TextStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = fontFamily(),
    brush = secondaryGradient(),
)

/**
 * Lyric highlighted style
 */
@Composable
fun lyricHighlightedStyle(): TextStyle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = fontFamily(),
    brush = primaryGradient(),
)

/**
 * Lyric normal style
 */
@Composable
fun lyricNormalStyle(): TextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = fontFamily(),
    color = Color(0xFF808090),
)

/**
 * Button text style
 */
@Composable
fun buttonStyle(): TextStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = fontFamily(),
    color = Color.White,
    textAlign = TextAlign.Center,
    letterSpacing = 1.sp,
)

/**
 * Chip/Tag style
 */
@Composable
fun chipStyle(): TextStyle = TextStyle(
    fontSize = 11.sp,
    fontWeight = FontWeight.Medium,
    fontFamily = fontFamily(),
    color = Color(0xFFB8B8C8),
    letterSpacing = 0.5.sp,
)
