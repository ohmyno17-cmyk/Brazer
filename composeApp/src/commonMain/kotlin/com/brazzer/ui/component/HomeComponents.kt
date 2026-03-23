package com.brazzer.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.brazzer.ui.theme.*

// ==========================================
// 🏠 STYLISH HOME HEADER 🏠
// ==========================================

@Composable
fun StylishHomeHeader(
    modifier: Modifier = Modifier,
    greeting: String = "Good Evening",
    userName: String = "Music Lover",
    avatarUrl: String? = null,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "header")
    
    val shimmer by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "shimmer",
    )
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        brazzerPrimary.copy(alpha = 0.1f),
                        Color.Transparent,
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
                ),
            )
            .padding(horizontal = 16.dp, vertical = 20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = greeting,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF808090),
                )
                GradientText(
                    text = userName,
                    gradientColors = listOf(Color.White, Color(0xFFB8B8C8)),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            
            // Avatar with glow
            GlowingAvatar(
                size = 48.dp,
                glowColor = brazzerPrimary,
            ) {
                if (avatarUrl != null) {
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "Avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        modifier = Modifier.padding(8.dp),
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

// ==========================================
// 🔥 TRENDING SECTION 🔥
// ==========================================

@Composable
fun TrendingSection(
    modifier: Modifier = Modifier,
    title: String = "Trending Now",
    items: List<TrendingItem> = sampleTrendingItems(),
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            GradientText(
                text = title,
                gradientColors = listOf(gradientFireStart, gradientFireEnd),
                style = MaterialTheme.typography.headlineSmall,
            )
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                FireEqualizer(isPlaying = true, barCount = 5)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "HOT",
                    style = MaterialTheme.typography.labelSmall,
                    color = gradientFireEnd,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items) { item ->
                TrendingCard(item = item)
            }
        }
    }
}

data class TrendingItem(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val plays: String,
)

@Composable
fun TrendingCard(
    modifier: Modifier = Modifier,
    item: TrendingItem,
) {
    var isHovered by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.05f else 1f,
        animationSpec = tween(200),
        label = "scale",
    )
    
    Card(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .scale(scale),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = md_theme_dark_surfaceVariant,
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            
            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f),
                                Color.Black,
                            ),
                        ),
                    ),
            )
            
            // Content
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFB8B8C8),
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = brazzerSecondary,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.plays,
                        style = MaterialTheme.typography.labelSmall,
                        color = brazzerSecondary,
                    )
                }
            }
        }
    }
}

// ==========================================
// 🎵 QUICK PLAY SECTION 🎵
// ==========================================

@Composable
fun QuickPlaySection(
    modifier: Modifier = Modifier,
    onShuffleClick: () -> Unit = {},
    onRecentClick: () -> Unit = {},
    onLikedClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        QuickPlayButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Shuffle,
            text = "Shuffle",
            gradientColors = listOf(gradientPrimaryStart, gradientPrimaryEnd),
            onClick = onShuffleClick,
        )
        
        QuickPlayButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.History,
            text = "Recent",
            gradientColors = listOf(gradientSecondaryStart, gradientSecondaryEnd),
            onClick = onRecentClick,
        )
        
        QuickPlayButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Favorite,
            text = "Liked",
            gradientColors = listOf(gradientAccentStart, gradientAccentEnd),
            onClick = onLikedClick,
        )
    }
}

@Composable
fun QuickPlayButton(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    gradientColors: List<Color>,
    onClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100),
        label = "scale",
    )
    
    Card(
        modifier = modifier.scale(scale),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(gradientColors),
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

// ==========================================
// 📻 MOOD GRID 📻
// ==========================================

@Composable
fun MoodGrid(
    modifier: Modifier = Modifier,
    moods: List<MoodItem> = sampleMoods(),
    onMoodClick: (MoodItem) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Moods & Genres",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
        
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(moods) { mood ->
                MoodCard(mood = mood, onClick = { onMoodClick(mood) })
            }
        }
    }
}

data class MoodItem(
    val name: String,
    val emoji: String,
    val gradientColors: List<Color>,
)

@Composable
fun MoodCard(
    modifier: Modifier = Modifier,
    mood: MoodItem,
    onClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100),
        label = "scale",
    )
    
    Card(
        modifier = modifier
            .width(120.dp)
            .height(80.dp)
            .scale(scale),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(mood.gradientColors),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = mood.emoji,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = mood.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

// ==========================================
// 🎤 ARTIST SPOTLIGHT 🎤
// ==========================================

@Composable
fun ArtistSpotlight(
    modifier: Modifier = Modifier,
    artistName: String = "Featured Artist",
    imageUrl: String? = null,
    monthlyListeners: String = "1.2M",
    onPlayClick: () -> Unit = {},
) {
    NeonGlowCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        glowColor = brazzerPrimary,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
        ) {
            // Background gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                brazzerPrimary.copy(alpha = 0.3f),
                                md_theme_dark_background,
                            ),
                        ),
                    ),
            )
            
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Artist image
                GlowingAvatar(
                    size = 120.dp,
                    glowColor = brazzerSecondary,
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = artistName,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // Artist info
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    NeonBadge(text = "SPOTLIGHT", color = brazzerSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = artistName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$monthlyListeners monthly listeners",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB8B8C8),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    GradientButton(
                        text = "Play Now",
                        onClick = onPlayClick,
                        gradientColors = listOf(gradientPrimaryStart, gradientPrimaryEnd),
                    )
                }
            }
        }
    }
}

// ==========================================
// 📝 SAMPLE DATA 📝
// ==========================================

@Composable
fun sampleTrendingItems(): List<TrendingItem> = listOf(
    TrendingItem(
        title = "Blinding Lights",
        subtitle = "The Weeknd",
        imageUrl = "https://i.scdn.co/image/ab67616d0000b2738863bc11d2aa12b54f5aeb36",
        plays = "3.2B",
    ),
    TrendingItem(
        title = "Anti-Hero",
        subtitle = "Taylor Swift",
        imageUrl = "https://i.scdn.co/image/ab67616d0000b273b1c4b76e23414c9f996a6eee",
        plays = "1.8B",
    ),
    TrendingItem(
        title = "As It Was",
        subtitle = "Harry Styles",
        imageUrl = "https://i.scdn.co/image/ab67616d0000b27377fdcfda6535601aff081b6a",
        plays = "2.5B",
    ),
)

@Composable
fun sampleMoods(): List<MoodItem> = listOf(
    MoodItem("Chill", "😌", listOf(Color(0xFF7B61FF), Color(0xFF3D1F9F))),
    MoodItem("Energy", "⚡", listOf(Color(0xFFFF6B35), Color(0xFFE935C1))),
    MoodItem("Focus", "🎯", listOf(Color(0xFF00F5D4), Color(0xFF00B4D8))),
    MoodItem("Party", "🎉", listOf(Color(0xFFE935C1), Color(0xFFFFD93D))),
    MoodItem("Romance", "💕", listOf(Color(0xFFFF4DD0), Color(0xFFFF0844))),
    MoodItem("Sad", "😢", listOf(Color(0xFF5B5B8C), Color(0xFF2E2E5B))),
)

// ==========================================
// 📊 STATS CARD 📊
// ==========================================

@Composable
fun StatsCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color = brazzerPrimary,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = md_theme_dark_surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(28.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF808090),
            )
        }
    }
}
