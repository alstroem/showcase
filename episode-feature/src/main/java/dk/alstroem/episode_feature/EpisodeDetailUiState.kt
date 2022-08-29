package dk.alstroem.episode_feature

import dk.alstroem.episode.domain.model.Episode

data class EpisodeDetailUiState(
    val data: Episode = Episode()
)
