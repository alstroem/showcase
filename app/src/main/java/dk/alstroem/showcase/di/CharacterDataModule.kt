package dk.alstroem.showcase.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dk.alstroem.character.data.CharacterRepositoryImpl
import dk.alstroem.character.data.remote.CharacterRemoteDataSource
import dk.alstroem.character.domain.CharacterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDataModule {

    @Singleton
    @Provides
    fun providesCharacterRemoteDataSource(client: ApolloClient) = CharacterRemoteDataSource(client)

    @Singleton
    @Provides
    fun providesCharacterRepository(
        remoteDataSource: CharacterRemoteDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }
}
