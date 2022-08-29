package dk.alstroem.location.data

import dk.alstroem.location.data.remote.LocationRemoteDataSource
import dk.alstroem.location.domain.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource
): LocationRepository {
    override fun getLocationPagingSource(): LocationPagingSource {
        return LocationPagingSource(remoteDataSource)
    }
}
