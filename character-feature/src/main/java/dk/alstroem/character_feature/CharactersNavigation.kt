package dk.alstroem.character_feature

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

const val CharacterRoutePattern = "characters"

fun NavGraphBuilder.charactersScreen() {
    composable(CharacterRoutePattern) {
        val viewModel: CharactersViewModel = hiltViewModel()
        val characterItems = viewModel.charactersFlow.collectAsLazyPagingItems()
        CharactersScreen(characterItems = characterItems)
    }
}
