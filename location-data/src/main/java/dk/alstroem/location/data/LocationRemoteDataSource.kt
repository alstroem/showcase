package dk.alstroem.location.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dk.alstroem.network_lib.Either
import dk.alstroem.network_lib.safeQuery
import dk.alstroem.rocketreserver.LocationListQuery
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val client: ApolloClient
) {
    suspend fun fetchLocationList(
        page: Int
    ): Either<LocationListQuery.Locations> {
        return safeQuery {
            client.query(LocationListQuery(Optional.Present(page)))
                .execute()
                .dataAssertNoErrors
                .locations
        }
    }
}
