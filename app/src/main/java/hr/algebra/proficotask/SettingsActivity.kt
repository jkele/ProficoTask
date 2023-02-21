package hr.algebra.proficotask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.proficotask.adapter.ALL_GENRES
import hr.algebra.proficotask.adapter.FAVORITE_GENRES
import hr.algebra.proficotask.adapter.FavoriteGenreRecyclerAdapter
import hr.algebra.proficotask.bottomsheet.ManageGenresBottomsheet
import hr.algebra.proficotask.database.model.GenreDb
import hr.algebra.proficotask.databinding.ActivitySettingsBinding
import hr.algebra.proficotask.helpers.startActivity
import hr.algebra.proficotask.network.model.Genre
import hr.algebra.proficotask.viewmodel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var mAuth: FirebaseAuth

    private val adapter:  FavoriteGenreRecyclerAdapter by lazy {
        FavoriteGenreRecyclerAdapter(
            this,
            viewModel.getGenresForUser(mAuth.currentUser!!.uid),
            null,
            FAVORITE_GENRES,
            {
                viewModel.insertFavoriteGenre(it, mAuth.currentUser!!.uid)
            },
            {
                viewModel.deleteFavoriteGenre(it)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.rvFavoriteGenres.layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteGenres.adapter = adapter

        viewModel.getLiveGenresForUser(mAuth.currentUser!!.uid).observe(this) { favoriteGenres ->
            adapter.submitList(favoriteGenres as ArrayList<GenreDb>)
        }

        viewModel.allGenresList.observe(this) { allGenres ->
            setupListeners(allGenres)
        }
        viewModel.getAllGenres()
    }

    private fun setupListeners(genresList: ArrayList<Genre>) {

        binding.btnLogOut.setOnClickListener {
            mAuth.signOut()

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut()

            this.startActivity<LoginActivity>()
        }

        binding.btnSaveChanges.setOnClickListener {
            this.startActivity<MainActivity>()
        }

        binding.btnManageGenres.setOnClickListener {
            val adapter =
                FavoriteGenreRecyclerAdapter(
                    this,
                    viewModel.getGenresForUser(mAuth.currentUser!!.uid),
                    genresList,
                    ALL_GENRES,
                    {
                        viewModel.insertFavoriteGenre(it, mAuth.currentUser!!.uid)
                    },
                    {
                        viewModel.deleteFavoriteGenre(it)
                    })
            val bottomsheetManageGenres = ManageGenresBottomsheet(adapter)
            bottomsheetManageGenres.show(supportFragmentManager, "ManageGenres")
        }
    }
}