package hr.algebra.proficotask.network.model

data class Genre(
    val id: Int,
    val name: String,
    val imageBackground: String,
    val games: ArrayList<GenreGame>
)