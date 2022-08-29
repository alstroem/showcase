package dk.alstroem.character.data

import dk.alstroem.character.domain.model.CharacterData
import dk.alstroem.rocketreserver.CharacterListQuery

internal fun CharacterListQuery.Characters.asDomain(): List<CharacterData> {
    return results.mapNotNull { it?.asDomain() }
}

internal fun CharacterListQuery.Result.asDomain(): CharacterData {
    return CharacterData(
        id = id ?: "",
        image = image ?: ""
    )
}
