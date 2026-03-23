package com.brazzer.di

import com.brazzer.viewModel.AlbumViewModel
import com.brazzer.viewModel.AnalyticsViewModel
import com.brazzer.viewModel.ArtistViewModel
import com.brazzer.viewModel.HomeViewModel
import com.brazzer.viewModel.LibraryDynamicPlaylistViewModel
import com.brazzer.viewModel.LibraryViewModel
import com.brazzer.viewModel.LocalPlaylistViewModel
import com.brazzer.viewModel.LogInViewModel
import com.brazzer.viewModel.MoodViewModel
import com.brazzer.viewModel.MoreAlbumsViewModel
import com.brazzer.viewModel.NotificationViewModel
import com.brazzer.viewModel.NowPlayingBottomSheetViewModel
import com.brazzer.viewModel.PlaylistViewModel
import com.brazzer.viewModel.PodcastViewModel
import com.brazzer.viewModel.RecentlySongsViewModel
import com.brazzer.viewModel.SearchViewModel
import com.brazzer.viewModel.SettingsViewModel
import com.brazzer.viewModel.SharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        single {
            SharedViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        single {
            SearchViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            NowPlayingBottomSheetViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryDynamicPlaylistViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AlbumViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            HomeViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            SettingsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ArtistViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            PlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LogInViewModel(
                get(),
            )
        }
        viewModel {
            PodcastViewModel(
                get(),
            )
        }
        viewModel {
            MoreAlbumsViewModel(
                get(),
            )
        }
        viewModel {
            RecentlySongsViewModel(
                get(),
            )
        }
        viewModel {
            LocalPlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            NotificationViewModel(
                get(),
            )
        }
        viewModel {
            MoodViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AnalyticsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
    }