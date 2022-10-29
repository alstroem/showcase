package dk.alstroem.character_feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import dk.alstroem.character.domain.model.CharacterData

@Composable
internal fun CharactersScreen(characterItems: LazyPagingItems<CharacterData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(characterItems.itemCount) { index ->
            characterItems[index]?.let {
                CharacterCard(data = it)
            }
        }
    }
}

@Composable
fun CharacterCard(
    data: CharacterData,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = data.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Text(
                text = data.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun CharacterCardPreview() {
    CharacterCard(
        data = CharacterData(
            id = "",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            name = "Name"
        )
    )
}
