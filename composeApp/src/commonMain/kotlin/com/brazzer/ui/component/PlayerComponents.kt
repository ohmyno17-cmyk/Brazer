package com.brazzer.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.brazzer.ui.theme.*
import kotlin.math.PI
import kotlin.math.sin

// ==========================================
// 🎵 FULL SCREEN PLAYER 🎵
// ==========================================

@Composable
fun StylishFullPlayer(
    modifier: Modifier = Modifier,
    songTitle: String = "Not Playing",
    artistName: String = "-",
    albumArtUrl: String? = null,
    isPlaying: Boolean = false,
    progress: Float = 0f,
    duration: String = "0:00",
    currentPosition: String = "0:00",
    isLiked: Boolean = false,
    isShuffle: Boolean = false,
    repeatMode: Int = 0, // 0: off, 1: all, 2: one
    onPlayPauseClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onSeek: (Float) -> Unit = {},
    onLikeClick: () -> Unit = {},
    onShuffleClick: () -> Unit = {},
    onRepeatClick: () -> Unit = {},
) {
    val infiniteTransition = rememberInfiniteTransition(label = "player")
    
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isPlaying) 1.02f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "pulse",
    )
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        md_theme_dark_background,
                        Color(0xFF0F0F18),
                        md_theme_dark_background,
                    ),
                ),
            ),
    ) {
        // Background album art blur effect
        AsyncImage(
            model = albumArtUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 0.2f
                    blurRadius = 50.dp.toPx()
                },
            contentScale = ContentScale.Crop,
        )
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp),
                    )
                }
                
                Text(
                    text = "NOW PLAYING",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF808090),
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold,
                )
                
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = Color.White,
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(0.5f))
            
            // Album art with vinyl effect
            VinylAlbumArt(
                albumArtUrl = albumArtUrl,
                isPlaying = isPlaying,
                modifier = Modifier
                    .size(280.dp)
                    .scale(pulseScale),
            )
            
            Spacer(modifier = Modifier.weight(0.5f))
            
            // Song info
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = songTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                GradientText(
                    text = artistName,
                    gradientColors = listOf(brazzerSecondary, brazzerPrimary),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Stylish progress bar
            StylishProgressBar(
                progress = progress,
                duration = duration,
                currentPosition = currentPosition,
                onSeek = onSeek,
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Main controls
            MainPlayerControls(
                isPlaying = isPlaying,
                isShuffle = isShuffle,
                repeatMode = repeatMode,
                onPlayPauseClick = onPlayPauseClick,
                onNextClick = onNextClick,
                onPreviousClick = onPreviousClick,
                onShuffleClick = onShuffleClick,
                onRepeatClick = onRepeatClick,
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Secondary controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ControlButton(
                    icon = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    onClick = onLikeClick,
                    isActive = isLiked,
                    activeColor = brazzerPrimary,
                )
                
                ControlButton(
                    icon = Icons.Outlined.Lyrics,
                    onClick = { },
                )
                
                ControlButton(
                    icon = Icons.Outlined.QueueMusic,
                    onClick = { },
                )
                
                ControlButton(
                    icon = Icons.Outlined.Share,
                    onClick = { },
                )
            }
        }
    }
}

// ==========================================
// 💿 VINYL ALBUM ART 💿
// ==========================================

@Composable
fun VinylAlbumArt(
    modifier: Modifier = Modifier,
    albumArtUrl: String?,
    isPlaying: Boolean,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "vinyl")
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotation",
    )
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        // Vinyl record background
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(if (isPlaying) rotation else 0f),
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 2
            
            // Vinyl grooves
            drawCircle(
                color = Color(0xFF1A1A1A),
                radius = radius,
            )
            
            repeat(20) { index ->
                drawCircle(
                    color = Color(0xFF252525).copy(alpha = 0.5f + index * 0.02f),
                    radius = radius - index * 6.dp.toPx(),
                    style = Stroke(width = 2.dp.toPx()),
                )
            }
            
            // Center label
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        brazzerPrimary.copy(alpha = 0.8f),
                        brazzerPrimary.copy(alpha = 0.4f),
                    ),
                ),
                radius = radius * 0.3f,
            )
            
            // Center hole
            drawCircle(
                color = md_theme_dark_background,
                radius = 8.dp.toPx(),
            )
        }
        
        // Album art in center
        Card(
            modifier = Modifier.size(120.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            AsyncImage(
                model = albumArtUrl,
                contentDescription = "Album Art",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

// ==========================================
// 📊 STYLISH PROGRESS BAR 📊
// ==========================================

@Composable
fun StylishProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    duration: String,
    currentPosition: String,
    onSeek: (Float) -> Unit,
) {
    var sliderPosition by remember { mutableStateOf<Float?>(null) }
    var width by remember { mutableStateOf(0f) }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        sliderPosition = (offset.x / size.width).coerceIn(0f, 1f)
                        onSeek(sliderPosition!!)
                    }
                }
                .onGloballyPositioned { coordinates ->
                    width = coordinates.size.width.toFloat()
                },
        ) {
            // Background track
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFF2D2D3D)),
            )
            
            // Progress with gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(4.dp)
                    .align(Alignment.CenterStart)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(gradientPrimaryStart, gradientSecondaryStart),
                        ),
                        shape = RoundedCornerShape(2.dp),
                    ),
            )
            
            // Glowing thumb
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (progress * width).dp - 8.dp)
                    .size(16.dp)
                    .drawBehind {
                        drawIntoCanvas { canvas ->
                            val paint = Paint().apply {
                                asFrameworkPaint().apply {
                                    setShadowLayer(
                                        12f,
                                        0f,
                                        0f,
                                        brazzerPrimary.toArgb(),
                                    )
                                }
                            }
                            canvas.drawCircle(
                                center = Offset(size.width / 2, size.height / 2),
                                radius = size.minDimension / 2,
                                paint = paint,
                            )
                        }
                    }
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.White, brazzerPrimary),
                        ),
                        CircleShape,
                    ),
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = currentPosition,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF808090),
            )
            Text(
                text = duration,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF808090),
            )
        }
    }
}

// ==========================================
// 🎮 MAIN PLAYER CONTROLS 🎮
// ==========================================

@Composable
fun MainPlayerControls(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    isShuffle: Boolean,
    repeatMode: Int,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onShuffleClick: () -> Unit,
    onRepeatClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Shuffle button
        ControlButton(
            icon = Icons.Default.Shuffle,
            onClick = onShuffleClick,
            isActive = isShuffle,
            activeColor = brazzerSecondary,
            size = 28.dp,
        )
        
        // Previous button
        ControlButton(
            icon = Icons.Default.SkipPrevious,
            onClick = onPreviousClick,
            size = 36.dp,
        )
        
        // Play/Pause button with glow
        PlayPauseButton(
            isPlaying = isPlaying,
            onClick = onPlayPauseClick,
        )
        
        // Next button
        ControlButton(
            icon = Icons.Default.SkipNext,
            onClick = onNextClick,
            size = 36.dp,
        )
        
        // Repeat button
        ControlButton(
            icon = when (repeatMode) {
                2 -> Icons.Default.RepeatOne
                else -> Icons.Default.Repeat
            },
            onClick = onRepeatClick,
            isActive = repeatMode > 0,
            activeColor = brazzerSecondary,
            size = 28.dp,
        )
    }
}

// ==========================================
// ▶️ PLAY/PAUSE BUTTON ▶️
// ==========================================

@Composable
fun PlayPauseButton(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onClick: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = if (isPlaying) 0.8f else 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "glow",
    )
    
    Box(
        modifier = modifier
            .size(72.dp)
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        asFrameworkPaint().apply {
                            setShadowLayer(
                                30f * glowIntensity,
                                0f,
                                0f,
                                brazzerPrimary.copy(alpha = glowIntensity).toArgb(),
                            )
                        }
                    }
                    canvas.drawCircle(
                        center = Offset(size.width / 2, size.height / 2),
                        radius = size.minDimension / 2,
                        paint = paint,
                    )
                }
            }
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(gradientPrimaryStart, gradientPrimaryEnd),
                ),
            )
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onClick() })
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play",
            tint = Color.White,
            modifier = Modifier.size(36.dp),
        )
    }
}

// ==========================================
// 🎛️ CONTROL BUTTON 🎛️
// ==========================================

@Composable
fun ControlButton(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    isActive: Boolean = false,
    activeColor: Color = brazzerPrimary,
    size: Dp = 32.dp,
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = tween(100),
        label = "scale",
    )
    
    Box(
        modifier = modifier
            .size(size + 16.dp)
            .scale(scale)
            .then(
                if (isActive) {
                    Modifier.background(
                        activeColor.copy(alpha = 0.2f),
                        CircleShape,
                    )
                } else {
                    Modifier
                }
            )
            .clip(CircleShape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isActive) activeColor else Color.White,
            modifier = Modifier.size(size),
        )
    }
}

// ==========================================
// 🎚️ VOLUME SLIDER 🎚️
// ==========================================

@Composable
fun VolumeSlider(
    modifier: Modifier = Modifier,
    volume: Float,
    onVolumeChange: (Float) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = Icons.Outlined.VolumeDown,
            contentDescription = "Volume",
            tint = Color(0xFF808090),
            modifier = Modifier.size(20.dp),
        )
        
        Slider(
            value = volume,
            onValueChange = onVolumeChange,
            modifier = Modifier.weight(1f),
            valueRange = 0f..1f,
            colors = SliderDefaults.colors(
                thumbColor = brazzerPrimary,
                activeTrackColor = brazzerPrimary,
                inactiveTrackColor = Color(0xFF2D2D3D),
            ),
        )
        
        Icon(
            imageVector = Icons.Outlined.VolumeUp,
            contentDescription = "Volume",
            tint = Color(0xFF808090),
            modifier = Modifier.size(20.dp),
        )
    }
}

// ==========================================
// 🎤 LYRICS DISPLAY 🎤
// ==========================================

@Composable
fun LyricsDisplay(
    modifier: Modifier = Modifier,
    lyrics: List<LyricLine>,
    currentLineIndex: Int,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        items(lyrics.size) { index ->
            val isCurrentLine = index == currentLineIndex
            
            Text(
                text = lyrics[index].text,
                style = if (isCurrentLine) {
                    MaterialTheme.typography.titleMedium.copy(
                        brush = Brush.linearGradient(
                            colors = listOf(gradientPrimaryStart, gradientSecondaryStart),
                        ),
                    )
                } else {
                    MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF606070),
                    )
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}

data class LyricLine(
    val time: Long,
    val text: String,
)
