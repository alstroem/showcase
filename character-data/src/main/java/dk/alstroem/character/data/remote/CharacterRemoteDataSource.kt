package dk.alstroem.character.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dk.alstroem.network_lib.Either
import dk.alstroem.network_lib.safeQuery
import dk.alstroem.rocketreserver.CharacterListQuery

class CharacterRemoteDataSource(
    private val client: ApolloClient
) {
    suspend fun fetchCharacterList(page: Int): Either<CharacterListQuery.Characters> {
        return safeQuery {
            client.query(CharacterListQuery(Optional.Present(page)))
                .execute()
                .dataAssertNoErrors
                .characters
        }
    }
}
