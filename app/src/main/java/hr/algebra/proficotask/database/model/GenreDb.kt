package hr.algebra.proficotask.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "genres")
data class GenreDb(
    @PrimaryKey(autoGenerate = true)
    val idGenre: Int = 0,
    val id: Int,
    val name: String,
    val imageBackground: String,
    val userId: String
): Serializable