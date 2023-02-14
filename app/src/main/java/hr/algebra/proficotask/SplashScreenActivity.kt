package hr.algebra.proficotask

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import hr.algebra.proficotask.databinding.ActivitySplashScreenBinding
import hr.algebra.proficotask.helpers.callDelayed
import hr.algebra.proficotask.helpers.startActivity
import hr.algebra.proficotask.helpers.startAnimation
import hr.algebra.proficotask.viewmodel.SplashScreenViewModel

private const val DELAY = 3000L

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun redirect() {
        callDelayed(DELAY) {
            if (!viewModel.isGenreTableEmpty()) {
                this.startActivity<OnboardActivity>()
            } else {
                this.startActivity<MainActivity>()
            }

        }
    }

    private fun startAnimations() {
        binding.ivLogo.startAnimation(R.anim.blink)
    }
}