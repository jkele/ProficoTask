package hr.algebra.proficotask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import coil.load
import hr.algebra.proficotask.R
import hr.algebra.proficotask.network.model.GameScreenshot

class ImageSlideAdapter(
    private val context: Context,
    private val imageList: ArrayList<GameScreenshot>
): PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.image_slider_item, null)
        val ivImages = view.findViewById<ImageView>(R.id.ivImages)

        ivImages.load(imageList[position].image)

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}