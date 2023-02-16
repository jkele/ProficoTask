package hr.algebra.proficotask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.proficotask.adapter.GenreRecyclerAdapter
import hr.algebra.proficotask.databinding.ActivityOnboardingBinding
import hr.algebra.proficotask.viewmodel.OnboardViewModel

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    val viewModel: OnboardViewModel by viewModels()

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupMenu()
        setupListeners()

        mAuth = FirebaseAuth.getInstance()

        binding.rvGenres.layoutManager = LinearLayoutManager(this)

        viewModel.genreList.observe(this) { genreList ->
            val adapter = GenreRecyclerAdapter(this, genreList, {
                viewModel.insertFavoriteGenre(it, mAuth.currentUser!!.uid)
                setupMenu()
            }, {
                viewModel.deleteGenreById(it)
                setupMenu()
            })

            binding.rvGenres.adapter = adapter

        }

        viewModel.getGenres()
    }

    private fun setupMenu() {
        val genreCount = viewModel.getNumberOfGenres()
        if (genreCount == 0) {
            binding.tvGenresSelected.text = getString(R.string.no_genres_selected)
            binding.btnContinue.isEnabled = false
        } else if(genreCount == 1) {
            binding.tvGenresSelected.text = getString(R.string.one_genre_selected)
            binding.btnContinue.isEnabled = true
        } else if(genreCount > 1){
            binding.tvGenresSelected.text = resources.getString(R.string.genre_count_template, genreCount)
            binding.btnContinue.isEnabled = true
        }
    }

    private fun setupListeners() {
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}