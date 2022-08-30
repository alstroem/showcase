package dk.alstroem.episode.data

import dk.alstroem.episode.data.remote.EpisodeRemoteDataSource
import dk.alstroem.episode.domain.EpisodeRepository
import dk.alstroem.episode.domain.model.Episode
import dk.alstroem.network_lib.Either

class EpisodeRepositoryImpl(
    private val remoteDataSource: EpisodeRemoteDataSource
) : EpisodeRepository {
    override fun getEpisodePagingSource(): EpisodePagingSource {
        return EpisodePagingSource(remoteDataSource)
    }

    override suspend fun getEpisode(episodeId: String): Either<Episode> {
        return when (val result = remoteDataSource.fetchEpisode(episodeId)) {
            is Either.Error -> result
            is Either.Success -> Either.Success(result.data.asDomain())
        }
    }
}
