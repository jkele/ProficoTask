package hr.algebra.proficotask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.algebra.proficotask.database.GenreDatabase
import hr.algebra.proficotask.database.GenreRepository
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.network.Network
import hr.algebra.proficotask.network.model.Genre
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application): AndroidViewModel(application) {


    private val repository: GenreRepository
    val allGenresList = MutableLiveData<ArrayList<Genre>>()

    init {
        val genreDao = GenreDatabase.getDatabase(application)!!.genreDao()
        repository = GenreRepository(genreDao)
    }

    fun getLiveGenresForUser(userId: String): LiveData<List<GenreDb>> {
        return repository.getLiveFavoriteGenresForUser(userId)
    }

    fun getGenresForUser(userId: String): ArrayList<GenreDb> {
        return repository.getFavoriteGenresForUser(userId) as ArrayList<GenreDb>
    }

    fun getAllGenres() {
        viewModelScope.launch {
            allGenresList.value = Network().getGameService().getGenres().results
        }
    }

    fun insertFavoriteGenre(genre: Genre, userId: String) {
        repository.insertGenre(genre, userId)
    }

    fun deleteFavoriteGenre(id: Int) {
        repository.deleteGenreById(id)
    }


}