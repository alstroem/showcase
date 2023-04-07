package dk.alstroem.location.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dk.alstroem.network_lib.safeQuery
import dk.alstroem.rocketreserver.LocationListQuery

class LocationRemoteDataSource(
    private val client: ApolloClient
) {
    suspend fun fetchLocationList(page: Int): Result<LocationListQuery.Locations> {
        return safeQuery {
            client.query(LocationListQuery(Optional.Present(page)))
                .execute()
                .dataAssertNoErrors
                .locations
        }
    }
}
