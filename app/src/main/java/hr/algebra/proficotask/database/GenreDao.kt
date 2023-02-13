package hr.algebra.proficotask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.algebra.proficotask.database.model.GenreDb

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreDb)

    @Query("SELECT * FROM genres")
    fun getGenres(): LiveData<List<GenreDb>>

}