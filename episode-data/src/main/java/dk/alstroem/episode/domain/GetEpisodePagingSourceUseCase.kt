package dk.alstroem.episode.domain

import dk.alstroem.episode.data.EpisodePagingSource
import javax.inject.Inject

class GetEpisodePagingSourceUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(): EpisodePagingSource {
        return repository.getEpisodePagingSource()
    }
}
