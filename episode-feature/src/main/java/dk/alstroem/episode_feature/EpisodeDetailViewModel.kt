package dk.alstroem.episode_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.alstroem.episode.domain.GetEpisodeUseCase
import dk.alstroem.episode_feature.navigation.EpisodeDetailArgs
import dk.alstroem.network_lib.Either
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEpisode: GetEpisodeUseCase
) : ViewModel() {

    private val episodeDetailArgs = EpisodeDetailArgs(savedStateHandle)

    var uiState by mutableStateOf(EpisodeDetailUiState())
        private set

    init {
        fetchEpisode()
    }

    private fun fetchEpisode() {
        viewModelScope.launch {
            val episodeId = episodeDetailArgs.episodeId
            when (val result = getEpisode(episodeId)) {
                is Either.Error -> {
                    // TODO: Implement proper error handling
                    Timber.e("Error fetching episode: $episodeId")
                }
                is Either.Success -> {
                    uiState = uiState.copy(data = result.data)
                }
            }
        }
    }
}
