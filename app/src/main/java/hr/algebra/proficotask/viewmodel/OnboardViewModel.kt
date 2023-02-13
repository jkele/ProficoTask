package hr.algebra.proficotask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.algebra.proficotask.network.Network
import hr.algebra.proficotask.network.model.Genre
import kotlinx.coroutines.launch

class OnboardViewModel: ViewModel() {

    val genreList = MutableLiveData<ArrayList<Genre>>()

    fun getGenres() {
        viewModelScope.launch {
            genreList.value = Network().getGameService().getGenres().results
        }
    }

}