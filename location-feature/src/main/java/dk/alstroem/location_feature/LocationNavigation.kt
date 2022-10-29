package dk.alstroem.location_feature

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

const val LocationRoutePattern = "location"

fun NavGraphBuilder.locationScreen() {
    composable(LocationRoutePattern) {
        val viewModel: LocationsViewModel = hiltViewModel()
        val locationItems = viewModel.locationsFlow.collectAsLazyPagingItems()
        LocationsScreen(locationItems = locationItems)
    }
}
