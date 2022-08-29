package dk.alstroem.character.data

import dk.alstroem.character.data.remote.CharacterRemoteDataSource
import dk.alstroem.character.domain.CharacterRepository

class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource
): CharacterRepository {
    override fun getCharacterPagingSource(): CharacterPagingSource {
        return CharacterPagingSource(remoteDataSource)
    }
}
