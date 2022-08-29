package dk.alstroem.character.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dk.alstroem.character.data.remote.CharacterRemoteDataSource
import dk.alstroem.character.domain.model.CharacterData
import dk.alstroem.network_lib.Either
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : PagingSource<Int, CharacterData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        val nextPage = params.key ?: 1
        return when (val result = remoteDataSource.fetchCharacterList(nextPage)) {
            is Either.Error -> LoadResult.Error(result.exception)
            is Either.Success -> LoadResult.Page(
                data = result.data.asDomain(),
                prevKey = null,
                nextKey = result.data.info.next
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
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