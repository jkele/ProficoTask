package hr.algebra.proficotask.database

import androidx.lifecycle.LiveData
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.network.model.Genre

class GenreRepository(private val genreDao: GenreDao) {

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

    fun getNumberOfGenresForUser(userId: String): Int {
        return genreDao.getNumberOfGenresForUser(userId)
    }

    fun isGenreTableEmpty(userId: String): Boolean {
        return genreDao.getNumberOfGenresForUser(userId) > 0
    }

}