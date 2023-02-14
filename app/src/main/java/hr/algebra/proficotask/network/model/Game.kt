package hr.algebra.proficotask.network.model

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val name: String,
    val released: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    val rating: Double,
    val metacritic: Int,
    val genres: ArrayList<GameGenre>,
    @SerializedName("short_screenshots")
    val gameScreenshots: ArrayList<GameScreenshot>,
    val description: String,
    @SerializedName("description_raw")
    val descriptionRaw: String
) {
}

data class GameGenre(
    val id: Int,
    val name: String,
    @SerializedName("image_background")
    val imageBackground: String,
)
