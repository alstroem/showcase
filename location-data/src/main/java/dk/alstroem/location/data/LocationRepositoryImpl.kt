package dk.alstroem.location.data

import dk.alstroem.location.domain.LocationRepository
import dk.alstroem.location.domain.model.LocationList
import dk.alstroem.network_lib.Either
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource
): LocationRepository {
    override suspend fun getLocationList(page: Int): Either<LocationList> {
        return when (val result = remoteDataSource.fetchLocationList(page)) {
            is Either.Loading -> result
            is Either.Error -> result
            is Either.Success -> {
                Either.Success(result.data.asDomain())
            }
        }
    }
}
