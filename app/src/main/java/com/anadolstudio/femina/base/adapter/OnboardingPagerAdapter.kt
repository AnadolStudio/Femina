package com.anadolstudio.femina.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class OnboardingPagerAdapter(private val onboardingScreensIds: List<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val onboardingScreenLayoutId = onboardingScreensIds[position]
        val screenLayout = inflater.inflate(onboardingScreenLayoutId, container, false) as ViewGroup
        container.addView(screenLayout)
        return screenLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun getCount(): Int = onboardingScreensIds.size

}
