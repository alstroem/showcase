package dk.alstroem.character.domain

import dk.alstroem.character.data.CharacterPagingSource
import javax.inject.Inject

class GetCharacterPagingSourceUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(): CharacterPagingSource {
        return repository.getCharacterPagingSource()
    }
}
