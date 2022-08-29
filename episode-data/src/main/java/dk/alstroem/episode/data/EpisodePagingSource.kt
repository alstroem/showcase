package dk.alstroem.episode.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dk.alstroem.episode.data.remote.EpisodeRemoteDataSource
import dk.alstroem.episode.domain.model.Episode
import dk.alstroem.network_lib.Either
import javax.inject.Inject

class EpisodePagingSource @Inject constructor(
    private val remoteDataSource: EpisodeRemoteDataSource
): PagingSource<Int, Episode>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val nextPage = params.key ?: 1
        return when (val result = remoteDataSource.fetchEpisodeList(nextPage)) {
            is Either.Error -> LoadResult.Error(result.exception)
            is Either.Success -> LoadResult.Page(
                data = result.data.asDomain(),
                prevKey = null,
                nextKey = result.data.info.next
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
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
