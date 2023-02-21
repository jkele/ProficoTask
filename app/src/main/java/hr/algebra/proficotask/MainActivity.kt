package hr.algebra.proficotask

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.proficotask.adapter.GamePagingAdapter
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.databinding.ActivityMainBinding
import hr.algebra.proficotask.helpers.startActivity
import hr.algebra.proficotask.network.paging.GameDiff
import hr.algebra.proficotask.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAuth: FirebaseAuth

    private var favoriteGenresList: ArrayList<GenreDb>? = null
    private val pagingAdapter by lazy { GamePagingAdapter(this, GameDiff) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        mAuth = FirebaseAuth.getInstance()

        favoriteGenresList = viewModel.getGenresForUser(mAuth.currentUser!!.uid)

        binding.rvGames.layoutManager = LinearLayoutManager(this)
        binding.rvGames.adapter = pagingAdapter

        setupProgressBar()

        lifecycleScope.launch {
            val flow = viewModel.getGamesFlow(mAuth.currentUser!!.uid)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun setupProgressBar() {
        pagingAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                binding.progressBar.visibility = ProgressBar.VISIBLE
            } else {
                binding.progressBar.visibility = ProgressBar.GONE
            }
        }
    }

    private fun setupListeners() {
        binding.btnSettings.setOnClickListener {
            this.startActivity<SettingsActivity>()
        }
    }

}