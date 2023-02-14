package hr.algebra.proficotask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import hr.algebra.proficotask.database.GenreDatabase
import hr.algebra.proficotask.database.GenreRepository

class SplashScreenViewModel(application: Application): AndroidViewModel(application) {

    private val repository: GenreRepository

    init {
        val genreDao = GenreDatabase.getDatabase(application)!!.genreDao()
        repository = GenreRepository(genreDao)
    }

    fun isGenreTableEmpty(): Boolean {
        return repository.isGenreTableEmpty()
    }

}