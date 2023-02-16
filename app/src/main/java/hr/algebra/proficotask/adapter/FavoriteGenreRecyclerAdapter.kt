package hr.algebra.proficotask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.proficotask.R
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.databinding.FavoriteGenreItemBinding
import hr.algebra.proficotask.network.model.Genre

const val FAVORITE_GENRES = 0
const val ALL_GENRES = 1

class FavoriteGenreRecyclerAdapter(
    private val context: Context,
    private val favoriteGenresList: ArrayList<GenreDb>,
    private val allGenresList: ArrayList<Genre>?,
    private val genreType: Int,
    private val insertCallback: ((Genre) -> Unit),
    private val deleteCallback: ((Int) -> Unit)
) : RecyclerView.Adapter<FavoriteGenreRecyclerAdapter.FavoriteGenreViewHolder>() {

    private val favoriteStates = mutableMapOf<Int, Boolean>()

    init {
        allGenresList?.forEachIndexed { index, genre ->
            favoriteStates[index] = favoriteGenresList.any { it.id == genre.id }
        }
    }

    fun submitList(newFavoriteGenresList: ArrayList<GenreDb>) {
        newFavoriteGenresList.sortBy { it.name }
        this.favoriteGenresList.clear()
        this.favoriteGenresList.addAll(newFavoriteGenresList)
        notifyDataSetChanged()
    }

    class FavoriteGenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = FavoriteGenreItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGenreViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorite_genre_item, parent, false)
        return FavoriteGenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteGenreViewHolder, position: Int) {
        when (genreType) {
            0 -> {
                holder.binding.tvGenreName.text = favoriteGenresList[position].name
                holder.binding.ivChecked.visibility = ImageView.GONE

            }
            1 -> {
                holder.binding.tvGenreName.text = allGenresList!![position].name
                holder.binding.ivChecked.visibility = ImageView.GONE
                val genreIsFavorite = favoriteStates[position] ?: false

                //Pocetno postavljanje
                if (genreIsFavorite) {
                    setItemForeground(holder)
                } else {
                    removeItemForeground(holder)
                }

                //on click
                holder.binding.root.setOnClickListener {
                    favoriteStates[position] = !genreIsFavorite
                    if (genreIsFavorite) {
                        deleteCallback.invoke(allGenresList[position].id)
                    } else {
                        insertCallback.invoke(allGenresList[position])
                    }
                    notifyItemChanged(position)
                }
            }
        }

    }

    private fun setItemForeground(holder: FavoriteGenreViewHolder) {
        holder.binding.root.foreground =
            ContextCompat.getDrawable(context, R.drawable.foreground_manage_genre_pick)
        holder.binding.ivChecked.visibility = ImageView.VISIBLE
    }

    private fun removeItemForeground(holder: FavoriteGenreViewHolder) {
        holder.binding.root.foreground = null
        holder.binding.ivChecked.visibility = ImageView.GONE
    }

    override fun getItemCount(): Int {
        return if (allGenresList.isNullOrEmpty()) {
            favoriteGenresList!!.size
        } else {
            allGenresList.size
        }
    }

}