package dk.alstroem.character.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dk.alstroem.network_lib.safeQuery
import dk.alstroem.rocketreserver.CharacterListQuery

class CharacterRemoteDataSource(
    private val client: ApolloClient
) {
    suspend fun fetchCharacterList(page: Int): Result<CharacterListQuery.Characters> {
        return safeQuery {
            client.query(CharacterListQuery(Optional.Present(page)))
                .execute()
                .dataAssertNoErrors
                .characters
        }
    }
}
