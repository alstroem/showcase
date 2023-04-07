package dk.alstroem.character.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dk.alstroem.character.data.remote.CharacterRemoteDataSource
import dk.alstroem.character.domain.model.CharacterData
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : PagingSource<Int, CharacterData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        val nextPage = params.key ?: 1
        return remoteDataSource.fetchCharacterList(nextPage).fold(
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
