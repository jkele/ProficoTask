package hr.algebra.proficotask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.proficotask.network.Network
import hr.algebra.proficotask.network.model.Game
import hr.algebra.proficotask.network.model.GameScreenshot
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    val gameDetails = MutableLiveData<Game>()
    val gameScreenshots = MutableLiveData<ArrayList<GameScreenshot>>()

    fun getGameDetails(gameId: Int) {
        viewModelScope.launch {
            gameDetails.value = Network().getGameService().getGameDetails(gameId)
        }
    }

    fun getGameScreenshots(gameId: Int) {
        viewModelScope.launch {
            gameScreenshots.value = Network().getGameService().getGameScreenshots(gameId).results
        }
    }

}