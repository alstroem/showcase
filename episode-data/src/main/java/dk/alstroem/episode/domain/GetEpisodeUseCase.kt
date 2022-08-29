package dk.alstroem.episode.domain

import dk.alstroem.episode.domain.model.Episode
import dk.alstroem.network_lib.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(episodeId: String): Either<Episode> = withContext(Dispatchers.IO) {
        repository.getEpisode(episodeId)
    }
}
