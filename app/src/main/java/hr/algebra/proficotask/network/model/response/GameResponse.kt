package hr.algebra.proficotask.network.model.response

import hr.algebra.proficotask.network.model.Game

data class GameResponse(
    val next: String,
    val results: ArrayList<Game>
) {
}