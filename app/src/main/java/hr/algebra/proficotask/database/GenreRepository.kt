package hr.algebra.proficotask.database

import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.network.model.Genre

class GenreRepository(private val genreDao: GenreDao) {

    //val readAllGenres = genreDao.getGenres()

    fun getFavoriteGenres(): ArrayList<GenreDb> {
        return genreDao.getGenres() as ArrayList<GenreDb>
    }

    fun insertGenre(genre: Genre) {
        genreDao.insertGenre(genre.convertForDb())
    }

    fun deleteGenreById(id: Int) {
        genreDao.deleteGenreById(id)
    }

    fun getNumberOfGenres(): Int {
        return genreDao.getNumberOfGenres()
    }

}