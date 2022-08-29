package dk.alstroem.location.domain

import dk.alstroem.location.data.LocationPagingSource

interface LocationRepository {
    fun getLocationPagingSource(): LocationPagingSource
}
