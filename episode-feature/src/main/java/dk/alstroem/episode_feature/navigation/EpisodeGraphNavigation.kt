package dk.alstroem.episode_feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val EpisodeGraphRoutePattern = "episode"

fun NavGraphBuilder.episodeGraph(navController: NavController) {
    navigation(
        startDestination = EpisodesRoutePattern,
        route = EpisodeGraphRoutePattern
    ) {
        episodesScreen { episodeId -> navController.navigateToEpisodeDetail(episodeId) }
        episodeDetailScreen()
    }
}
