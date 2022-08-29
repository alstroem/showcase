package dk.alstroem.episode.domain.model

data class Episode(
    val id: String = "",
    val name: String = "",
    val episode: String = "",
    val airDate: String = "",
    val characters: List<EpisodeCharacter> = emptyList()
)
