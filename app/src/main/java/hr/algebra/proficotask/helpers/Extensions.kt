package hr.algebra.proficotask.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils

const val EXTRA_GAME_ID = "hr.algebra.proficotask.helpers.extraGameId"

inline fun<reified T : Activity> Context.startActivity()
        = startActivity(Intent(this, T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
})

inline fun<reified T : Activity> Context.startActivity(id: Int)
        = startActivity(Intent(this, T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    putExtra(EXTRA_GAME_ID, id)
})

fun View.startAnimation(animationId: Int)
        = startAnimation(AnimationUtils.loadAnimation(context, animationId))

fun callDelayed(delay: Long, function: Runnable) {
    Handler(Looper.getMainLooper()).postDelayed(
        function,
        delay
    )
}