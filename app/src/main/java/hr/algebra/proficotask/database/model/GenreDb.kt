package hr.algebra.proficotask.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hr.algebra.proficotask.network.model.GenreGame
import java.io.Serializable

@Entity(tableName = "genres")
data class GenreDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageBackground: String,
    val userId: String
): Serializable