package dk.alstroem.episode.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dk.alstroem.network_lib.Either
import dk.alstroem.network_lib.safeQuery
import dk.alstroem.rocketreserver.EpisodeDetailQuery
import dk.alstroem.rocketreserver.EpisodeListQuery

class EpisodeRemoteDataSource(
    private val client: ApolloClient
) {
    suspend fun fetchEpisodeList(
        page: Int
    ): Either<EpisodeListQuery.Episodes> {
        return safeQuery {
            client.query(EpisodeListQuery(Optional.Present(page)))
                .execute()
                .dataAssertNoErrors
                .episodes
        }
    }

    suspend fun fetchEpisode(episodeId: String): Either<EpisodeDetailQuery.Episode> {
        return safeQuery {
            client.query(EpisodeDetailQuery(episodeId))
                .execute()
                .dataAssertNoErrors
                .episode
        }
    }
}
