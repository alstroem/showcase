package dk.alstroem.episode_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.alstroem.episode.domain.GetEpisodePagingSourceUseCase
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodePagingSource: GetEpisodePagingSourceUseCase
): ViewModel() {

    val episodesFLow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        getEpisodePagingSource()
    }.flow.cachedIn(viewModelScope)
}
