package hr.algebra.proficotask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import coil.load
import hr.algebra.proficotask.adapter.ImageSlideAdapter
import hr.algebra.proficotask.databinding.ActivityGameBinding
import hr.algebra.proficotask.helpers.EXTRA_GAME_ID
import hr.algebra.proficotask.network.model.Game
import hr.algebra.proficotask.viewmodel.GameViewModel
import java.lang.StringBuilder

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    private lateinit var viewPagerAdapter: ImageSlideAdapter

    private var selectedGameId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedGameId = intent.getIntExtra(EXTRA_GAME_ID, 0)

        viewModel.gameDetails.observe(this) { game ->
            binding.ivGameImage.load(game.backgroundImage)
            binding.tvGameTitle.text = game.name
            binding.tvMetacritic.text = game.metacritic.toString()
            binding.tvAbout.text = game.descriptionRaw
            binding.tvPlatforms.text = getPlatformsString(game)
            binding.tvGenres.text = getGenreString(game)


            setEventImages()
        }

        viewModel.getGameDetails(selectedGameId)
    }

    private fun setEventImages() {
        viewModel.gameScreenshots.observe(this) {
            binding.imagesProgressBar.visibility = ProgressBar.VISIBLE

            viewPagerAdapter = ImageSlideAdapter(this, it)
            binding.viewpager.adapter = viewPagerAdapter
            binding.imageIndicator.setViewPager(binding.viewpager)

            binding.imagesProgressBar.visibility = ProgressBar.GONE
        }

        viewModel.getGameScreenshots(selectedGameId)
    }

    private fun getGenreString(game: Game): String {
        val genreBuilder = StringBuilder()
        game.genres.forEachIndexed { index, genre ->
            genreBuilder.append(genre.name)
            if (index != genreBuilder.lastIndex) genreBuilder.append(", ")
        }
        return genreBuilder.toString()
    }

    private fun getPlatformsString(game: Game): String {
        val platformsBuilder = StringBuilder()
        game.platforms.forEachIndexed { index, platforms ->
            platformsBuilder.append(platforms.platform.name)
            if (index != platformsBuilder.lastIndex) platformsBuilder.append(", ")
        }
        return platformsBuilder.toString()
    }

}