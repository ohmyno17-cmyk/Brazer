package com.brazzer.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brazzer.ui.theme.*

// ==========================================
// 🔮 NEON GLOW CARD 🔮
// ==========================================

@Composable
fun NeonGlowCard(
    modifier: Modifier = Modifier,
    glowColor: Color = brazzerPrimary,
    content: @Composable () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "intensity",
    )
    
    Card(
        modifier = modifier
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        this.asFrameworkPaint().apply {
                            setShadowLayer(
                                20f * glowIntensity,
                                0f,
                                0f,
                                glowColor.copy(alpha = glowIntensity).toArgb(),
                            )
                        }
                    }
                    canvas.drawRoundRect(
                        0f,
                        0f,
                        size.width,
                        size.height,
                        16.dp.toPx(),
                        16.dp.toPx(),
                        paint,
                    )
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = md_theme_dark_surface,
        ),
    ) {
        content()
    }
}

// ==========================================
// 🌊 GRADIENT BUTTON 🌊
// ==========================================

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    gradientColors: List<Color> = listOf(gradientPrimaryStart, gradientPrimaryEnd),
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100),
        label = "scale",
    )
    
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier
            .scale(scale)
            .background(
                brush = Brush.linearGradient(gradientColors),
                shape = RoundedCornerShape(12.dp),
            )
            .clip(RoundedCornerShape(12.dp)),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
            )
        },
    )
}

// ==========================================
// 🎵 MUSIC VISUALIZER 🎵
// ==========================================

@Composable
fun MusicVisualizer(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    barCount: Int = 5,
    barColors: List<Color> = listOf(
        visualizerBar1,
        visualizerBar2,
        visualizerBar3,
        visualizerBar4,
        visualizerBar5,
    ),
) {
    val infiniteTransition = rememberInfiniteTransition(label = "visualizer")
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        repeat(barCount) { index ->
            val animatedHeight by infiniteTransition.animateFloat(
                initialValue = 0.2f,
                targetValue = (0.3f + (index % 3) * 0.2f + kotlin.random.Random.nextFloat() * 0.5f).coerceIn(0.2f, 1f),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 300 + index * 100,
                        easing = FastOutSlowInEasing,
                    ),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "height$index",
            )
            
            val height = if (isPlaying) animatedHeight else 0.2f
            
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp * height)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                barColors[index % barColors.size],
                                barColors[index % barColors.size].copy(alpha = 0.5f),
                            ),
                        ),
                        shape = RoundedCornerShape(2.dp),
                    ),
            )
        }
    }
}

// ==========================================
// ✨ SHIMMER EFFECT ✨
// ==========================================

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
) {
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
    
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        shimmerBackground,
                        shimmerHighlight,
                        shimmerBackground,
                    ),
                    start = Offset(offset * 1000, 0f),
                    end = Offset(offset * 1000 + 500, 500f),
                ),
            ),
    )
}

// ==========================================
// 🔴 PULSATING DOT 🔴
// ==========================================

@Composable
fun PulsatingDot(
    modifier: Modifier = Modifier,
    color: Color = brazzerPrimary,
    size: Dp = 8.dp,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "scale",
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "alpha",
    )
    
    Box(
        modifier = modifier
            .size(size)
            .scale(scale)
            .background(color.copy(alpha = alpha), CircleShape),
    )
}

// ==========================================
// 🌈 GRADIENT TEXT 🌈
// ==========================================

@Composable
fun GradientText(
    text: String,
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(gradientPrimaryStart, gradientSecondaryStart),
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.titleLarge,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.copy(
            brush = Brush.linearGradient(gradientColors),
        ),
    )
}

// ==========================================
// 🎯 GLOWING CIRCLE AVATAR 🎯
// ==========================================

@Composable
fun GlowingAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    glowColor: Color = brazzerPrimary,
    content: @Composable () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "intensity",
    )
    
    Box(
        modifier = modifier
            .size(size)
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        this.asFrameworkPaint().apply {
                            setShadowLayer(
                                15f * glowIntensity,
                                0f,
                                0f,
                                glowColor.copy(alpha = glowIntensity).toArgb(),
                            )
                        }
                    }
                    canvas.drawCircle(
                        center = androidx.compose.ui.geometry.Offset(size.toPx() / 2, size.toPx() / 2),
                        radius = size.toPx() / 2,
                        paint = paint,
                    )
                }
            }
            .clip(CircleShape),
    ) {
        content()
    }
}

// ==========================================
// 📊 ANIMATED PROGRESS BAR 📊
// ==========================================

@Composable
fun AnimatedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(gradientPrimaryStart, gradientPrimaryEnd),
    backgroundColor: Color = Color(0xFF2D2D3D),
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "progress",
    )
    
    Box(
        modifier = modifier
            .height(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(2.dp))
            .background(backgroundColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress)
                .height(4.dp)
                .background(
                    brush = Brush.horizontalGradient(gradientColors),
                    shape = RoundedCornerShape(2.dp),
                ),
        )
    }
}

// ==========================================
// 🏷️ NEON BADGE 🏷️
// ==========================================

@Composable
fun NeonBadge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = brazzerSecondary,
) {
    Box(
        modifier = modifier
            .background(
                color.copy(alpha = 0.2f),
                RoundedCornerShape(12.dp),
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(color, color.copy(alpha = 0.5f)),
                ),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

// ==========================================
// 🎬 ANIMATED CARD 🎬
// ==========================================

@Composable
fun AnimatedCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = tween(100),
        label = "scale",
    )
    
    Card(
        modifier = modifier.scale(scale),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = md_theme_dark_surfaceVariant,
        ),
        onClick = onClick,
    ) {
        content()
    }
}

// ==========================================
// 🌟 SPOTLIGHT EFFECT 🌟
// ==========================================

@Composable
fun SpotlightCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "spotlight")
    
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "offsetX",
    )
    
    Box(
        modifier = modifier
            .drawBehind {
                val spotlightX = size.width * offsetX
                val spotlightY = size.height * 0.5f
                
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.1f),
                            Color.Transparent,
                        ),
                        center = Offset(spotlightX, spotlightY),
                        radius = size.width * 0.5f,
                    ),
                )
            }
            .clip(RoundedCornerShape(16.dp))
            .background(md_theme_dark_surfaceVariant),
    ) {
        content()
    }
}
