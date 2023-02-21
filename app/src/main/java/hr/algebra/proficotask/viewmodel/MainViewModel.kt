package hr.algebra.proficotask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import hr.algebra.proficotask.database.GenreDatabase
import hr.algebra.proficotask.database.GenreRepository
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.network.Network
import hr.algebra.proficotask.network.model.Game
import hr.algebra.proficotask.network.paging.GamePagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: GenreRepository

    init {
        val genreDao = GenreDatabase.getDatabase(application)!!.genreDao()
        repository = GenreRepository(genreDao)
    }

    fun getGamesFlow(userId: String): Flow<PagingData<Game>> {
        return Pager(PagingConfig(pageSize = 30)) {
            GamePagingSource(Network().getGameService(), getGenreIds(getGenresForUser(userId)))
        }.flow.cachedIn(viewModelScope)
    }

    fun getGenresForUser(userId: String): ArrayList<GenreDb> {
        return repository.getFavoriteGenresForUser(userId) as ArrayList<GenreDb>
    }

    private fun getGenreIds(genreList: ArrayList<GenreDb>): String {
        val genreStringBuilder = StringBuilder()
        genreList.forEachIndexed { index, genre ->
            genreStringBuilder.append(genre.id)
            if (index != genreList.lastIndex) genreStringBuilder.append(",")
        }
        return genreStringBuilder.toString()
    }

}