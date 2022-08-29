package dk.alstroem.navigation_ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn

sealed class NavScreen(val route: String) {
    object Episodes: NavScreen("episodes")
    object Characters: NavScreen("characters")
    object Locations: NavScreen("locations")
}

val navigationBarItem = mapOf(
    NavScreen.Episodes to Icons.Filled.Home,
    NavScreen.Characters to Icons.Filled.Face,
    NavScreen.Locations to Icons.Filled.LocationOn
)
