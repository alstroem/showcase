package dk.alstroem.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dk.alstroem.character_feature.charactersScreen
import dk.alstroem.episode_feature.navigation.EpisodeGraphRoutePattern
import dk.alstroem.episode_feature.navigation.episodeGraph
import dk.alstroem.location_feature.locationScreen

@Composable
fun ShowcaseNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = EpisodeGraphRoutePattern
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        episodeGraph(navHostController)
        charactersScreen()
        locationScreen()
    }
}
