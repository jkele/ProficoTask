package hr.algebra.proficotask.database

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.algebra.proficotask.database.model.GenreDb

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: GenreDb)

    @Query("SELECT * FROM genres")
    fun getGenres(): List<GenreDb>

    @Query("SELECT * FROM genres WHERE userId = :userId")
    fun getLiveGenresForUser(userId: String): LiveData<List<GenreDb>>

    @Query("SELECT * FROM genres WHERE userId = :userId")
    fun getGenresForUser(userId: String): List<GenreDb>

    @Query("DELETE FROM genres WHERE id = :genreId")
    fun deleteGenreById(genreId: Int)

    @Query("SELECT COUNT(*) FROM genres WHERE userId = :userId")
    fun getNumberOfGenresForUser(userId: String): Int

}