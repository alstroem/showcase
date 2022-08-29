package dk.alstroem.episode.domain

import dk.alstroem.episode.data.EpisodePagingSource
import dk.alstroem.episode.domain.model.Episode
import dk.alstroem.network_lib.Either

interface EpisodeRepository {
    fun getEpisodePagingSource(): EpisodePagingSource
    suspend fun getEpisode(episodeId: String): Either<Episode>
}