package com.anadolstudio.femina.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.anadolstudio.femina.R
import com.anadolstudio.femina.base.adapter.OnboardingPagerAdapter
import com.anadolstudio.femina.base.viewmodel.observe
import com.anadolstudio.femina.base.viewmodel.onboarding.BaseOnboardingViewModel
import com.anadolstudio.femina.base.viewmodel.onboarding.OnboardingViewState
import com.anadolstudio.femina.extension.lazyUnsafe
import com.anadolstudio.femina.extension.throttleClick
import com.anadolstudio.femina.ui.base.fragment.BaseFragment

abstract class BaseOnboardingFragment(
    @LayoutRes private val layoutId: Int = R.layout.fragment_onboarding_container
) : BaseFragment(layoutId) {

    private companion object {
        const val OFFSCREEN_PAGE_LIMIT = 3
    }

    override var isDarkStatusBarIcons = false

    abstract var viewModel: BaseOnboardingViewModel

    private val viewPager: ViewPager by lazyUnsafe {
        provideOnboardingViewPager()
    }

    abstract fun getPageLayoutIds(): List<Int>

    protected open var isCloseButtonVisible = false

    protected open fun provideOnboardingViewPager(): ViewPager =
        requireView().findViewById(R.id.fragment_onboarding_viewpager)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.startOnboarding(requireContext())

        observe(viewModel.state, this::render)
    }

    override fun onBackPressedInternal(): Boolean {
        viewModel.onBackPressed(viewPager.currentItem, requireContext())

        return true
    }

    protected open fun render(viewState: OnboardingViewState) {
        if (viewPager.adapter == null) {
            val screenLayoutIds = viewState.screens.map { it.layoutId }
            viewPager.adapter = OnboardingPagerAdapter(screenLayoutIds)
            viewPager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

                override fun onPageSelected(position: Int) = viewModel.onScreenSwiped(position, requireContext())
            })
        }

        viewPager.currentItem = viewState.currentScreenIndex
    }

    fun setupNextButton(nextButtonView: View) {
        nextButtonView.throttleClick {
            viewModel.onNextPressed(viewPager.currentItem, requireContext())
        }
    }

    fun setupCancelButton(cancelButtonView: View) {
        cancelButtonView.isVisible = isCloseButtonVisible

        cancelButtonView.throttleClick { viewModel.onCancel() }
    }

}
