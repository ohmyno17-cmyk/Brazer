package com.brazzer.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.brazzer.ui.theme.*

// ==========================================
// 🎵 VINYL LOADER 🎵
// ==========================================

@Composable
fun VinylLoader(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    color: Color = brazzerPrimary,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "vinyl")
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotation",
    )
    
    Box(
        modifier = modifier
            .size(size)
            .rotate(rotation),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Outer ring
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(color, color.copy(alpha = 0.3f)),
                ),
                radius = size.toPx() / 2,
                style = Stroke(width = 4.dp.toPx()),
            )
            
            // Inner grooves
            repeat(3) { index ->
                drawCircle(
                    color = color.copy(alpha = 0.2f + index * 0.1f),
                    radius = size.toPx() / 2 - (index + 1) * 8.dp.toPx(),
                    style = Stroke(width = 1.dp.toPx()),
                )
            }
            
            // Center hole
            drawCircle(
                color = md_theme_dark_background,
                radius = 8.dp.toPx(),
            )
        }
    }
}

// ==========================================
// 🌊 WAVE LOADER 🌊
// ==========================================

@Composable
fun WaveLoader(
    modifier: Modifier = Modifier,
    color: Color = brazzerSecondary,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(5) { index ->
            val animDelay = index * 100
            
            val height by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600, animDelay, FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "height$index",
            )
            
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp * height)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(color, color.copy(alpha = 0.5f)),
                        ),
                        shape = RoundedCornerShape(2.dp),
                    ),
            )
        }
    }
}

// ==========================================
// 🔮 ORBIT LOADER 🔮
// ==========================================

@Composable
fun OrbitLoader(
    modifier: Modifier = Modifier,
    size: Dp = 60.dp,
    primaryColor: Color = brazzerPrimary,
    secondaryColor: Color = brazzerSecondary,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "orbit")
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotation",
    )
    
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        // Center dot
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(primaryColor, primaryColor.copy(alpha = 0.5f)),
                    ),
                    CircleShape,
                ),
        )
        
        // Orbiting dot
        Box(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation),
            contentAlignment = Alignment.TopCenter,
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(secondaryColor, CircleShape),
            )
        }
    }
}

// ==========================================
// 🎨 SPLASH SCREEN 🎨
// ==========================================

@Composable
fun AnimatedSplashScreen(
    modifier: Modifier = Modifier,
    onAnimationComplete: () -> Unit = {},
) {
    val infiniteTransition = rememberInfiniteTransition(label = "splash")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "scale",
    )
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotation",
    )
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        md_theme_dark_background,
                        Color(0xFF0F0F1A),
                        md_theme_dark_background,
                    ),
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        // Background glow
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .rotate(rotation),
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        brazzerPrimary.copy(alpha = 0.3f),
                        Color.Transparent,
                    ),
                ),
                radius = size.minDimension / 2,
            )
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            // Logo with pulse
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .scale(scale),
            ) {
                VinylLoader(size = 100.dp, color = brazzerPrimary)
            }
            
            // App name with gradient
            GradientText(
                text = "brazzer",
                gradientColors = listOf(brazzerPrimary, brazzerSecondary),
                style = MaterialTheme.typography.displayMedium,
            )
            
            // Tagline
            Text(
                text = "Music Reimagined",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF808090),
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Loading indicator
            WaveLoader(color = brazzerSecondary)
        }
    }
}

// ==========================================
// 🎵 NOW PLAYING MINI WIDGET 🎵
// ==========================================

@Composable
fun NowPlayingMiniWidget(
    modifier: Modifier = Modifier,
    songTitle: String = "Not Playing",
    artistName: String = "-",
    albumArtUrl: String? = null,
    isPlaying: Boolean = false,
    progress: Float = 0f,
    onClick: () -> Unit = {},
) {
    NeonGlowCard(
        modifier = modifier.fillMaxWidth(),
        glowColor = if (isPlaying) brazzerPrimary else Color.Transparent,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Album art with glow
            GlowingAvatar(
                size = 56.dp,
                glowColor = if (isPlaying) brazzerPrimary else Color.Transparent,
            ) {
                AsyncImage(
                    model = albumArtUrl,
                    contentDescription = "Album Art",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Song info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = songTitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1,
                )
                
                Text(
                    text = artistName,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFB8B8C8),
                    maxLines = 1,
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Progress bar
                AnimatedProgressBar(
                    progress = progress,
                    gradientColors = if (isPlaying) {
                        listOf(gradientPrimaryStart, gradientPrimaryEnd)
                    } else {
                        listOf(Color(0xFF3D3D5C), Color(0xFF3D3D5C))
                    },
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Visualizer
            MusicVisualizer(
                isPlaying = isPlaying,
                barCount = 4,
            )
        }
    }
}

// ==========================================
// 🎧 HEADPHONE BOUNCE ANIMATION 🎧
// ==========================================

@Composable
fun HeadphoneBounce(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "bounce")
    
    val bounce by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isPlaying) -10f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "bounce",
    )
    
    Box(
        modifier = modifier
            .offset(y = bounce.dp)
            .size(48.dp),
    ) {
        // Headphone icon representation using circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            
            // Headband
            drawArc(
                color = brazzerPrimary,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
                topLeft = Offset(center.x - 15.dp.toPx(), center.y - 20.dp.toPx()),
                size = Size(30.dp.toPx(), 20.dp.toPx()),
            )
            
            // Left ear cup
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(brazzerPrimary, brazzerSecondary),
                ),
                radius = 10.dp.toPx(),
                center = Offset(center.x - 15.dp.toPx(), center.y + 5.dp.toPx()),
            )
            
            // Right ear cup
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(brazzerSecondary, brazzerPrimary),
                ),
                radius = 10.dp.toPx(),
                center = Offset(center.x + 15.dp.toPx(), center.y + 5.dp.toPx()),
            )
        }
    }
}

// ==========================================
// 🔥 FIRE EQUALIZER 🔥
// ==========================================

@Composable
fun FireEqualizer(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    barCount: Int = 8,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "fire")
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        repeat(barCount) { index ->
            val height by infiniteTransition.animateFloat(
                initialValue = 0.2f,
                targetValue = (0.3f + kotlin.random.Random.nextFloat() * 0.7f).coerceIn(0.2f, 1f),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 200 + index * 50,
                        easing = FastOutSlowInEasing,
                    ),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "height$index",
            )
            
            val animatedHeight = if (isPlaying) height else 0.2f
            
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(32.dp * animatedHeight)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                gradientFireEnd,      // Yellow at top
                                gradientFireStart,    // Red in middle
                                Color.Transparent,    // Transparent at bottom
                            ),
                        ),
                        shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp),
                    ),
            )
        }
    }
}

// ==========================================
// 💫 LOADING DOTS 💫
// ==========================================

@Composable
fun LoadingDots(
    modifier: Modifier = Modifier,
    color: Color = brazzerPrimary,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(3) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(400, index * 150, FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "scale$index",
            )
            
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(400, index * 150, FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "alpha$index",
            )
            
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .scale(scale)
                    .background(color.copy(alpha = alpha), CircleShape),
            )
        }
    }
}
