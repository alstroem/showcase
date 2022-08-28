package dk.alstroem.location.domain

import dk.alstroem.location.domain.model.LocationList
import dk.alstroem.network_lib.Either

interface LocationRepository {
    suspend fun getLocationList(page: Int): Either<LocationList>
}
