package dk.alstroem.location_feature

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dk.alstroem.location.domain.model.Location
import dk.alstroem.location_ui.R
import kotlinx.coroutines.launch

@Composable
fun LocationsScreen(locationItems: LazyPagingItems<Location>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val listState = rememberLazyListState()

        LocationList(
            listState = listState,
            items = locationItems
        )

        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        val composableScope = rememberCoroutineScope()

        AnimatedVisibility(visible = showButton) {
            ScrollToTopButton(modifier = Modifier.padding(18.dp)) {
                composableScope.launch {
                    listState.scrollToItem(index = 0)
                }
            }
        }
    }
}

@Composable
fun LocationList(
    listState: LazyListState,
    items: LazyPagingItems<Location>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
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

@Composable
fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null)
    }
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

@Preview
@Composable
fun ScrollToTopButtonPreview() {
    ScrollToTopButton {}
}
