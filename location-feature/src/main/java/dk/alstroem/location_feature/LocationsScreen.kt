package dk.alstroem.location_feature

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dk.alstroem.location.domain.model.Location
import dk.alstroem.location_ui.R

@Composable
fun LocationsScreen(
    viewModel: LocationsViewModel
) {

    val locationItems = viewModel.locationsFlow.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = locationItems,
            key = { it.id }
        ) { item ->
            if (item != null) {
                LocationItem(item = item)
            } else {
                // Placeholder
            }
        }
    }
}

@Composable
fun LocationItem(
    item: Location,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            LocationText(stringRes = R.string.location_name, value = item.name)
            Spacer(modifier = modifier.height(8.dp))
            LocationText(stringRes = R.string.location_type, value = item.type)
            Spacer(modifier = modifier.height(8.dp))
            LocationText(stringRes = R.string.location_dimension, value = item.dimension)
        }
    }
}

@Composable
fun LocationText(
    @StringRes stringRes: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(stringRes, value),
        style = MaterialTheme.typography.titleMedium
    )
}

@Preview
@Composable
fun LocationItemPreview() {
    val mockedLocation = Location("", "Name", "Dimension", "type")
    LocationItem(item = mockedLocation)
}

@Preview
@Composable
fun LocationTextPreview() {
    LocationText(stringRes = R.string.location_name, value = "Name")
}
