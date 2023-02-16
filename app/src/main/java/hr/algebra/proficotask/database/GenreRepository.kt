package hr.algebra.proficotask.database

import androidx.lifecycle.LiveData
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.network.model.Genre

class GenreRepository(private val genreDao: GenreDao) {

    //val readAllGenres = genreDao.getGenres()


    fun getFavoriteGenres(): ArrayList<GenreDb> {
        return genreDao.getGenres() as ArrayList<GenreDb>
    }

    fun getLiveFavoriteGenresForUser(userId: String): LiveData<List<GenreDb>> {
        return genreDao.getLiveGenresForUser(userId)
    }

    fun getFavoriteGenresForUser(userId: String): List<GenreDb> {
        return genreDao.getGenresForUser(userId)
    }

    fun insertGenre(genre: Genre, userId: String) {
        genreDao.insertGenre(genre.convertForDb(userId))
    }

    fun deleteGenreById(id: Int) {
        genreDao.deleteGenreById(id)
    }

    fun getNumberOfGenres(): Int {
        return genreDao.getNumberOfGenres()
    }

    fun isGenreTableEmpty(): Boolean {
        return genreDao.getNumberOfGenres() > 0
    }

}