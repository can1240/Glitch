package com.glitch.music.di

import com.glitch.music.viewModel.AlbumViewModel
import com.glitch.music.utils.VersionManager
import com.glitch.music.viewModel.AnalyticsViewModel
import com.glitch.music.viewModel.ListenTogetherSettingsViewModel
import com.glitch.music.viewModel.ListenTogetherViewModel
import com.glitch.music.viewModel.ArtistViewModel
import com.glitch.music.viewModel.BrowseViewModel
import com.glitch.music.viewModel.CommentsViewModel
import com.glitch.music.viewModel.HomeViewModel
import com.glitch.music.viewModel.ImportViewModel
import com.glitch.music.viewModel.LibraryDynamicPlaylistViewModel
import com.glitch.music.viewModel.LibraryViewModel
import com.glitch.music.viewModel.LocalPlaylistViewModel
import com.glitch.music.viewModel.LogInViewModel
import com.glitch.music.viewModel.LoginSyncHostViewModel
import com.glitch.music.viewModel.LoginSyncSenderViewModel
import com.glitch.music.viewModel.MoodViewModel
import com.glitch.music.viewModel.MoreAlbumsViewModel
import com.glitch.music.viewModel.NotificationViewModel
import com.glitch.music.viewModel.NowPlayingBottomSheetViewModel
import com.glitch.music.viewModel.PlaylistViewModel
import com.glitch.music.viewModel.PodcastViewModel
import com.glitch.music.viewModel.SearchViewModel
import com.glitch.music.viewModel.AutoEqViewModel
import com.glitch.music.viewModel.SettingsViewModel
import com.glitch.music.viewModel.SharedViewModel
import com.glitch.music.viewModel.SongSelectionViewModel
import com.glitch.music.viewModel.TasteViewModel
import com.glitch.music.viewModel.WrappedViewModel
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
                get(),
            )
        }
        viewModel {
            SongSelectionViewModel(
                get(),
                get(),
            )
        }
        // Only one of these resolves on a given platform: Desktop binds the host repository, Android the
        // sender. Koin resolves dependencies when a ViewModel is created, not when it is declared.
        viewModel { LoginSyncHostViewModel(get()) }
        viewModel { LoginSyncSenderViewModel(get()) }
        viewModel { CommentsViewModel(get(), get()) }
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
                get(),
                get(),
            )
        }
        viewModel {
            LibraryDynamicPlaylistViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ImportViewModel(
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
            AutoEqViewModel(
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
                get(),
                get(),
            )
        }
        viewModel {
            ArtistViewModel(
                get(),
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
            BrowseViewModel(
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
        viewModel {
            WrappedViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            TasteViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ListenTogetherSettingsViewModel(get())
        }
        viewModel {
            ListenTogetherViewModel(
                repository = get(),
                dataStore = get(),
                bridge = get(),
            )
        }

    }