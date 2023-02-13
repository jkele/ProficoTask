package hr.algebra.proficotask.network.model

import com.google.gson.annotations.SerializedName
import hr.algebra.proficotask.database.model.GenreDb

data class Genre(
    val id: Int,
    val name: String,
    @SerializedName("image_background")
    val imageBackground: String,
    val games: ArrayList<GenreGame>
) {

    fun convertForDb(): GenreDb {
        return GenreDb(id, name, imageBackground)
    }

}