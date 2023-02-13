package hr.algebra.proficotask.database

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.algebra.proficotask.database.model.GenreDb

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: GenreDb)

    @Query("SELECT * FROM genres")
    fun getGenres(): LiveData<List<GenreDb>>

    @Query("DELETE FROM genres WHERE id = :genreId")
    fun deleteGenreById(genreId: Int)

    @Query("SELECT COUNT(*) FROM genres")
    fun getNumberOfGenres(): Int

}