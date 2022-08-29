package dk.alstroem.location.domain

import dk.alstroem.location.data.LocationPagingSource
import javax.inject.Inject

class GetLocationPagingSourceUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(): LocationPagingSource {
        return repository.getLocationPagingSource()
    }
}
