package hr.algebra.proficotask.network.model.response

import hr.algebra.proficotask.network.model.GameScreenshot

data class ScreenshotsResponse(
    val results: ArrayList<GameScreenshot>
) {
}