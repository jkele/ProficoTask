package hr.algebra.proficotask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.algebra.proficotask.database.GenreDatabase
import hr.algebra.proficotask.database.GenreRepository
import hr.algebra.proficotask.network.Network
import hr.algebra.proficotask.network.model.Genre
import kotlinx.coroutines.launch

class OnboardViewModel(application: Application): AndroidViewModel(application) {

    val genreList = MutableLiveData<ArrayList<Genre>>()
    private val repository: GenreRepository

    init {
        val genreDao = GenreDatabase.getDatabase(application)!!.genreDao()
        repository = GenreRepository(genreDao)
    }

    fun getGenres() {
        viewModelScope.launch {
            genreList.value = Network().getGameService().getGenres().results
        }
    }

    fun insertFavoriteGenre(genre: Genre, userId: String) {
        repository.insertGenre(genre, userId)
    }

    fun deleteGenreById(id: Int) {
        repository.deleteGenreById(id)
    }

    fun getNumberOfGenresForUser(userId: String): Int {
        return repository.getNumberOfGenresForUser(userId)
    }

}