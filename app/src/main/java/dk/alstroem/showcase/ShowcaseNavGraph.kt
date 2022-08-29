package dk.alstroem.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dk.alstroem.character_feature.CharactersScreen
import dk.alstroem.episode_feature.EpisodesScreen
import dk.alstroem.location_feature.LocationsScreen
import dk.alstroem.navigation_ui.NavScreen

@Composable
fun ShowcaseNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = NavScreen.Episodes.route
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NavScreen.Episodes.route) {
            EpisodesScreen(
                navController = navHostController,
                viewModel = hiltViewModel()
            )
        }

        composable(route = NavScreen.Characters.route) {
            CharactersScreen()
        }

        composable(route = NavScreen.Locations.route) {
            LocationsScreen(viewModel = hiltViewModel())
        }
    }
}