package dk.alstroem.episode.data

import dk.alstroem.episode.data.remote.EpisodeRemoteDataSource
import dk.alstroem.episode.domain.EpisodeRepository
import dk.alstroem.episode.domain.model.Episode

class EpisodeRepositoryImpl(
    private val remoteDataSource: EpisodeRemoteDataSource
) : EpisodeRepository {
    override fun getEpisodePagingSource(): EpisodePagingSource {
        return EpisodePagingSource(remoteDataSource)
    }

    override suspend fun getEpisode(episodeId: String): Result<Episode> {
        return remoteDataSource.fetchEpisode(episodeId).map { it.asDomain() }
    }
}
