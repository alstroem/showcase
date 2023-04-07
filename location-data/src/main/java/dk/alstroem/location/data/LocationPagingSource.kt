package dk.alstroem.location.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dk.alstroem.location.data.remote.LocationRemoteDataSource
import dk.alstroem.location.domain.model.Location
import javax.inject.Inject

class LocationPagingSource @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource
) : PagingSource<Int, Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val nextPage = params.key ?: 1
        return remoteDataSource.fetchLocationList(nextPage).fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.asDomain(),
                    prevKey = null,
                    nextKey = it.info.next
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
