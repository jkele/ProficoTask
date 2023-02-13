package hr.algebra.proficotask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import com.squareup.picasso.Picasso
import hr.algebra.proficotask.R
import hr.algebra.proficotask.databinding.GenreItemViewBinding
import hr.algebra.proficotask.network.model.Genre

class GenreRecyclerAdapter(
    private val context: Context,
    private val genreList: ArrayList<Genre>
): RecyclerView.Adapter<GenreRecyclerAdapter.GenreViewHolder>() {

    class GenreViewHolder(view: View): RecyclerView.ViewHolder(view) {
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
        Picasso.get().load(genre.imageBackground).into(holder.binding.ivGenreImage)
        holder.binding.ivGenreImage.imageAlpha = 190

        holder.binding.tvPopularGame.text = popularGame?.name

    }

    override fun getItemCount(): Int {
        return genreList.size
    }

}