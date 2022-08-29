package dk.alstroem.episode.data

import dk.alstroem.episode.domain.model.Episode
import dk.alstroem.episode.domain.model.EpisodeCharacter
import dk.alstroem.rocketreserver.EpisodeDetailQuery
import dk.alstroem.rocketreserver.EpisodeListQuery

internal fun EpisodeListQuery.Episodes.asDomain(): List<Episode> {
    return results.mapNotNull { it?.asDomain() }
}

internal fun EpisodeListQuery.Result.asDomain(): Episode {
    return Episode(
        id = id ?: "",
        name = name ?: "",
        episode = episode ?: "",
        airDate = air_date ?: ""
    )
}

internal fun EpisodeDetailQuery.Episode.asDomain(): Episode {
    return Episode(
        id = id ?: "",
        name = name ?: "",
        episode = episode ?: "",
        airDate = air_date ?: "",
        characters = characters.mapNotNull { it?.asDomain() }
    )
}

internal fun EpisodeDetailQuery.Character.asDomain(): EpisodeCharacter {
    return EpisodeCharacter(
        id = id ?: "",
        name = name ?: "",
        image = image ?: "",
         gender = gender ?: "",
        species = species ?: "",
        status = status ?: ""
    )
}
