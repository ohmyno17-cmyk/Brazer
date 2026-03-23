package com.brazzer.ui.navigation.graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.brazzer.ui.navigation.destination.list.AlbumDestination
import com.brazzer.ui.navigation.destination.list.ArtistDestination
import com.brazzer.ui.navigation.destination.list.LocalPlaylistDestination
import com.brazzer.ui.navigation.destination.list.MoreAlbumsDestination
import com.brazzer.ui.navigation.destination.list.PlaylistDestination
import com.brazzer.ui.navigation.destination.list.PodcastDestination
import com.brazzer.ui.screen.library.LocalPlaylistScreen
import com.brazzer.ui.screen.other.AlbumScreen
import com.brazzer.ui.screen.other.ArtistScreen
import com.brazzer.ui.screen.other.MoreAlbumsScreen
import com.brazzer.ui.screen.other.PlaylistScreen
import com.brazzer.ui.screen.other.PodcastScreen

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun NavGraphBuilder.listScreenGraph(
    innerPadding: PaddingValues,
    navController: NavController,
) {
    composable<AlbumDestination> { entry ->
        val data = entry.toRoute<AlbumDestination>()
        AlbumScreen(
            browseId = data.browseId,
            navController = navController,
        )
    }
    composable<ArtistDestination> { entry ->
        val data = entry.toRoute<ArtistDestination>()
        ArtistScreen(
            channelId = data.channelId,
            navController = navController,
        )
    }
    composable<LocalPlaylistDestination> { entry ->
        val data = entry.toRoute<LocalPlaylistDestination>()
        LocalPlaylistScreen(
            id = data.id,
            navController = navController,
        )
    }
    composable<MoreAlbumsDestination> { entry ->
        val data = entry.toRoute<MoreAlbumsDestination>()
        MoreAlbumsScreen(
            innerPadding = innerPadding,
            navController = navController,
            type = data.type,
            id = data.id,
        )
    }
    composable<PlaylistDestination> { entry ->
        val data = entry.toRoute<PlaylistDestination>()
        PlaylistScreen(
            playlistId = data.playlistId,
            isYourYouTubePlaylist = data.isYourYouTubePlaylist,
            navController = navController,
        )
    }
    composable<PodcastDestination> { entry ->
        val data = entry.toRoute<PodcastDestination>()
        PodcastScreen(
            podcastId = data.podcastId,
            navController = navController,
        )
    }
}