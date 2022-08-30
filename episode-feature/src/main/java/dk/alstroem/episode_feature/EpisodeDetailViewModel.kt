package dk.alstroem.episode_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.alstroem.episode.domain.GetEpisodeUseCase
import dk.alstroem.network_lib.Either
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisode: GetEpisodeUseCase
) : ViewModel() {

    var uiState by mutableStateOf(EpisodeDetailUiState())
        private set

    fun fetchEpisode(episodeId: String) {
        viewModelScope.launch {
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
