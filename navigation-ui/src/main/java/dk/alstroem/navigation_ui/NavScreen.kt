package dk.alstroem.navigation_ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen(val route: String, val icon: ImageVector) {
    object Episodes: NavScreen("episodes", Icons.Filled.Home)
    object Characters: NavScreen("characters", Icons.Filled.Face)
    object Locations: NavScreen("locations", Icons.Filled.LocationOn)
}

val navigationBarItem = listOf(
    NavScreen.Episodes,
    NavScreen.Characters,
    NavScreen.Locations
)
