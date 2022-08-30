package dk.alstroem.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dk.alstroem.character_feature.CharactersScreen
import dk.alstroem.episode_feature.EpisodeDetailScreen
import dk.alstroem.episode_feature.EpisodesScreen
import dk.alstroem.location_feature.LocationsScreen
import dk.alstroem.navigation_ui.NavScreen

@Composable
fun ShowcaseNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = NavScreen.EpisodeGraph.route
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        episodeGraph(navHostController)

        composable(route = NavScreen.Characters.route) {
            CharactersScreen(viewModel = hiltViewModel())
        }

        composable(route = NavScreen.Locations.route) {
            LocationsScreen(viewModel = hiltViewModel())
        }
    }
}

fun NavGraphBuilder.episodeGraph(navController: NavController) {
    navigation(startDestination = NavScreen.Episodes.route, route = NavScreen.EpisodeGraph.route) {
        composable(route = NavScreen.Episodes.route) {
            EpisodesScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }

        composable(route = NavScreen.EpisodeDetail.route) { backStackEntry ->
            EpisodeDetailScreen(
                viewModel = hiltViewModel(),
                episodeId = backStackEntry.arguments?.getString("episodeId") ?: ""
            )
        }
    }
}
