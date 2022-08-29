package dk.alstroem.episode.domain.model

data class EpisodeCharacter(
    val id: String,
    val name: String,
    val image: String,
    val gender: String,
    val species: String,
    val status: String
)
