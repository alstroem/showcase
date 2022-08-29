package dk.alstroem.showcase.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dk.alstroem.episode.data.EpisodeRepositoryImpl
import dk.alstroem.episode.data.remote.EpisodeRemoteDataSource
import dk.alstroem.episode.domain.EpisodeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodeDataModule {

    @Singleton
    @Provides
    fun provideEpisodeRemoteDataSource(client: ApolloClient) = EpisodeRemoteDataSource(client)

    @Singleton
    @Provides
    fun providesEpisodeRepository(
        remoteDataSource: EpisodeRemoteDataSource
    ): EpisodeRepository {
        return EpisodeRepositoryImpl(remoteDataSource)
    }
}
