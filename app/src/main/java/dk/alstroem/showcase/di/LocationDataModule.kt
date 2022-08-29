package dk.alstroem.showcase.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dk.alstroem.location.data.LocationRepositoryImpl
import dk.alstroem.location.data.remote.LocationRemoteDataSource
import dk.alstroem.location.domain.LocationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationDataModule {

    @Singleton
    @Provides
    fun provideLocationRemoteDataSource(client: ApolloClient) = LocationRemoteDataSource(client)

    @Provides
    @Singleton
    fun providesLocationRepository(
        remoteDataSource: LocationRemoteDataSource
    ): LocationRepository {
        return LocationRepositoryImpl(remoteDataSource)
    }
}
