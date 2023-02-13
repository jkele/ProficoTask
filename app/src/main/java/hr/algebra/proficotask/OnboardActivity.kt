package hr.algebra.proficotask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.proficotask.adapter.GenreRecyclerAdapter
import hr.algebra.proficotask.databinding.ActivityOnboardingBinding
import hr.algebra.proficotask.viewmodel.OnboardViewModel

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    val viewModel: OnboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvGenres.layoutManager = LinearLayoutManager(this)

        viewModel.genreList.observe(this) { genreList ->
            val adapter = GenreRecyclerAdapter(this, genreList, {
                viewModel.insertFavoriteGenre(it)
            }, {
                viewModel.deleteGenreById(it)
            })

            binding.rvGenres.adapter = adapter

        }

        viewModel.getGenres()
    }
}