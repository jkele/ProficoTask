package hr.algebra.proficotask.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import hr.algebra.proficotask.R
import hr.algebra.proficotask.databinding.GenreItemViewBinding
import hr.algebra.proficotask.network.model.Genre

class GenreRecyclerAdapter(
    private val context: Context,
    private val genreList: ArrayList<Genre>,
    private val insertCallback: ((Genre) -> Unit),
    private val deleteCallback: ((Int) -> Unit)
) : RecyclerView.Adapter<GenreRecyclerAdapter.GenreViewHolder>() {

    private val selectedPositions = SparseBooleanArray()

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = GenreItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.genre_item_view, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genreList[position]
        val popularGame = genre.games.maxByOrNull { it.added }

        holder.binding.tvGenreTitle.text = genre.name
        holder.binding.ivGenreImage.load(genre.imageBackground)
        holder.binding.ivGenreImage.imageAlpha = 190

        holder.binding.tvPopularGame.text = popularGame?.name

        if (selectedPositions.get(position, false)) {
            holder.binding.root.foreground =
                ContextCompat.getDrawable(context, R.drawable.foreground_genre_pick)
            holder.binding.ivChecked.visibility = ImageView.VISIBLE
            insertCallback.invoke(genre)
        } else {
            holder.binding.root.foreground = null
            holder.binding.ivChecked.visibility = ImageView.GONE
            deleteCallback.invoke(genre.id)
        }

        holder.binding.root.setOnClickListener {
            selectedPositions.put(position, !selectedPositions.get(position, false))
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return genreList.size
    }

}