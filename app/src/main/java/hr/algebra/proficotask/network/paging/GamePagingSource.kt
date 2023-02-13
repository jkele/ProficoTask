package hr.algebra.proficotask.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hr.algebra.proficotask.network.GameService
import hr.algebra.proficotask.network.model.Game
import java.lang.Exception

class GamePagingSource(
    private val service: GameService,
    private val genres: String
    ): PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.getGamesForGenres(genres, nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = parsePageFromUrl(response.next)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun parsePageFromUrl(url: String): Int {
        return url[url.lastIndex].digitToInt()
    }
}