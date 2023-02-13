package hr.algebra.proficotask.database

import hr.algebra.proficotask.network.model.Genre

class GenreRepository(private val genreDao: GenreDao) {

    val readAllGenres = genreDao.getGenres()

    suspend fun insertGenre(genre: Genre) {
        genreDao.insertGenre(genre.convertForDb())
    }

}