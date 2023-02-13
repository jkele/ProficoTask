package hr.algebra.proficotask.network.model.response

import hr.algebra.proficotask.network.model.Genre

data class GenreResponse(
    val count: Int,
    val next: Int?,
    val previous: Int?,
    val results: ArrayList<Genre>
)