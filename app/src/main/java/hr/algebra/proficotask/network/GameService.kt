package hr.algebra.proficotask.network

import hr.algebra.proficotask.network.model.response.GameResponse
import hr.algebra.proficotask.network.model.response.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY_ATT = "?key=8c3c3f0f59a8406b91764f02682c7574"

interface GameService {

    @GET("genres${API_KEY_ATT}")
    suspend fun getGenres(): GenreResponse

    @GET("games${API_KEY_ATT}")
    suspend fun getGamesForGenres(
        @Query("genres") genres:String,
        @Query("page") page: Int
    ): GameResponse
}