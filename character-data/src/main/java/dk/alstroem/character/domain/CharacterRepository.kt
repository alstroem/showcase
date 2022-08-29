package dk.alstroem.character.domain

import dk.alstroem.character.data.CharacterPagingSource

interface CharacterRepository {
    fun getCharacterPagingSource(): CharacterPagingSource
}
