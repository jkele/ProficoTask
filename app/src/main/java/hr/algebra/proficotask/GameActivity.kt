package hr.algebra.proficotask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import coil.load
import hr.algebra.proficotask.adapter.ImageSlideAdapter
import hr.algebra.proficotask.databinding.ActivityGameBinding
import hr.algebra.proficotask.viewmodel.GameViewModel

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    private lateinit var viewPagerAdapter: ImageSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.gameDetails.observe(this) { game ->
            binding.ivGameImage.load(game.backgroundImage)
            binding.tvGameTitle.text = game.name
            binding.tvMetacritic.text = game.metacritic.toString()
            binding.tvAbout.text = game.descriptionRaw

            setEventImages()
        }

        viewModel.getGameDetails(3498)
    }

    private fun setEventImages() {
        viewModel.gameScreenshots.observe(this) {
            binding.imagesProgressBar.visibility = ProgressBar.VISIBLE

            viewPagerAdapter = ImageSlideAdapter(this, it)
            binding.viewpager.adapter = viewPagerAdapter

            binding.imagesProgressBar.visibility = ProgressBar.GONE
        }

        viewModel.getGameScreenshots(3498)
    }
}