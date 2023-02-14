package hr.algebra.proficotask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import hr.algebra.proficotask.R
import hr.algebra.proficotask.databinding.GameItemViewBinding
import hr.algebra.proficotask.network.model.Game

class GamePagingAdapter(
    private val context: Context,
    diffCallback: DiffUtil.ItemCallback<Game>
): PagingDataAdapter<Game, GamePagingAdapter.GameViewHolder>(diffCallback) {

    class GameViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = GameItemViewBinding.bind(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)

        holder.binding.tvGameTitle.text = game?.name
        holder.binding.ivGameImage.load(game?.backgroundImage){
            transformations(RoundedCornersTransformation(topLeft = 32f, topRight = 32f))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.game_item_view, parent, false)
        return GameViewHolder(view)
    }

}