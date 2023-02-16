package hr.algebra.proficotask.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hr.algebra.proficotask.adapter.ALL_GENRES
import hr.algebra.proficotask.adapter.FavoriteGenreRecyclerAdapter
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.databinding.BottomsheetManageGenresBinding
import hr.algebra.proficotask.network.model.Genre

class ManageGenresBottomsheet(
    private val adapter: FavoriteGenreRecyclerAdapter
): BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetManageGenresBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetManageGenresBinding.inflate(inflater, container, false)

        binding.rvGenres.layoutManager = LinearLayoutManager(context)
        binding.rvGenres.adapter = adapter

        return binding.root
    }

}