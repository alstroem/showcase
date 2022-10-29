package dk.alstroem.episode_feature.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import dk.alstroem.episode_feature.EpisodesScreen
import dk.alstroem.episode_feature.EpisodesViewModel

internal const val EpisodesRoutePattern = "$EpisodeGraphRoutePattern/list"

fun NavGraphBuilder.episodesScreen(onNavigateToEpisodeDetail: (episodeId: String) -> Unit) {
    composable(EpisodesRoutePattern) {
        val viewModel: EpisodesViewModel = hiltViewModel()
        val episodeItems = viewModel.episodesFLow.collectAsLazyPagingItems()
        EpisodesScreen(episodeItems = episodeItems, onNavigateToEpisodeDetail = onNavigateToEpisodeDetail)
    }
}
