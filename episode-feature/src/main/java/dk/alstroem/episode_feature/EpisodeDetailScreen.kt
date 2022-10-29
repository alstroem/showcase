package dk.alstroem.episode_feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dk.alstroem.episode.domain.model.Episode
import dk.alstroem.episode.domain.model.EpisodeCharacter
import dk.alstroem.episode_ui.R
import kotlinx.coroutines.flow.flow

@Composable
fun EpisodeDetailScreen(
    uiState: EpisodeDetailUiState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                EpisodeTextSection(data = uiState.data)
            }

            item {
                Text(
                    text = stringResource(id = R.string.episode_characters_lbl),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            items(uiState.data.characters) { character ->
                CharacterItem(data = character)
            }
        }
    }
}

@Composable
fun EpisodeTextSection(
    data: Episode,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = data.episode, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.name, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.airDate, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun CharacterItem(
    data: EpisodeCharacter,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = data.image,
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )

            Column(modifier = Modifier.padding(12.dp)) {
                CharacterInfo(value = data.name)
                Spacer(modifier = Modifier.height(8.dp))
                CharacterInfo(value = data.gender)
                Spacer(modifier = Modifier.height(8.dp))
                CharacterInfo(value = data.species)
                Spacer(modifier = Modifier.height(8.dp))
                CharacterInfo(value = data.status)
            }
        }
    }
}

@Composable
fun CharacterInfo(value: String) {
    Text(text = value, style = MaterialTheme.typography.bodyLarge)
}

@Preview
@Composable
fun EpisodeTextSectionPreview() {
    EpisodeTextSection(
        data = Episode(
            name = "Name",
            episode = "S01E02",
            airDate = "Decempber 15, 2013"
        )
    )
}

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        data = EpisodeCharacter(
            id = "",
            name = "Name",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            gender = "Gender",
            species = "Species",
            status = "Statues"
        )
    )
}

@Preview
@Composable
fun CharacterInfoPreview() {
    CharacterInfo(value = "Value")
}
