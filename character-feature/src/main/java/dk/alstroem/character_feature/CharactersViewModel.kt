package dk.alstroem.character_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.alstroem.character.domain.GetCharacterPagingSourceUseCase
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    getCharacterPagingSource: GetCharacterPagingSourceUseCase
) : ViewModel() {
    val charactersFlow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        getCharacterPagingSource()
    }.flow.cachedIn(viewModelScope)
}
