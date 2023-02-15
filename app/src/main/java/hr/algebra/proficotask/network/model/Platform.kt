package hr.algebra.proficotask.network.model

data class PlatformResponse(
    val platform: Platform
) {
}

data class Platform(
    val id: Int,
    val name: String
) {

}