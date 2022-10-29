package dk.alstroem.episode_feature.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dk.alstroem.episode_feature.EpisodeDetailScreen
import dk.alstroem.episode_feature.EpisodeDetailViewModel

private const val EpisodeIdArgs = "episodeId"
private const val EpisodeDetailRoutePattern = "$EpisodeGraphRoutePattern/detail/"

internal class EpisodeDetailArgs(val episodeId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[EpisodeIdArgs]) as String)
}

internal fun NavGraphBuilder.episodeDetailScreen() {
    composable("$EpisodeDetailRoutePattern{$EpisodeIdArgs}") {
        val viewModel: EpisodeDetailViewModel = hiltViewModel()
        val uiState = viewModel.uiState
        EpisodeDetailScreen(uiState = uiState)
    }
}

internal fun NavController.navigateToEpisodeDetail(episodeId: String) {
    navigate("$EpisodeDetailRoutePattern$episodeId")
}
