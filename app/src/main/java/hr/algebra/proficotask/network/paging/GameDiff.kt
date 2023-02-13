package hr.algebra.proficotask.network.paging

import androidx.recyclerview.widget.DiffUtil
import hr.algebra.proficotask.network.model.Game

object GameDiff: DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }
}