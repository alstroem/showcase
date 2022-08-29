package dk.alstroem.location_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.alstroem.location.domain.GetLocationPagingSourceUseCase
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getLocationPagingSource: GetLocationPagingSourceUseCase
): ViewModel() {

    val locationsFlow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        getLocationPagingSource()
    }.flow.cachedIn(viewModelScope)

}
