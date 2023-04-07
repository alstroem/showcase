package dk.alstroem.episode.domain

import dk.alstroem.episode.domain.model.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(episodeId: String): Result<Episode> = withContext(Dispatchers.IO) {
        repository.getEpisode(episodeId)
    }
}
