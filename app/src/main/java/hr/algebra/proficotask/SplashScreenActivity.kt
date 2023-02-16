package hr.algebra.proficotask

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
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
    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        startAnimations()
        redirect()
    }

    private fun redirect() {
        val isGenreTableEmpty = viewModel.isGenreTableEmpty()
        callDelayed(DELAY) {
            if (firebaseAuth.currentUser == null) {
                this.startActivity<LoginActivity>()
                finish()
            }
            if (firebaseAuth.currentUser != null && isGenreTableEmpty) {
                this.startActivity<MainActivity>()
                finish()
            } else if(firebaseAuth.currentUser != null && !isGenreTableEmpty) {
                this.startActivity<OnboardActivity>()
                finish()
            }
        }
    }

    private fun startAnimations() {
        binding.ivLogo.startAnimation(R.anim.blink)
    }
}