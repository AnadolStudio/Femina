package com.anadolstudio.femina.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.anadolstudio.femina.R
import com.anadolstudio.femina.base.fragment.BaseOnboardingFragment
import com.anadolstudio.femina.base.viewmodel.obtainViewModel
import com.anadolstudio.femina.base.viewmodel.onboarding.BaseOnboardingViewModel
import com.anadolstudio.femina.base.viewmodel.onboarding.OnboardingViewState
import com.anadolstudio.femina.databinding.FragmentOnboardingContainerBinding
import com.anadolstudio.femina.extension.lazyUnsafe

class FirstOnboardingFragment : BaseOnboardingFragment() {

    companion object {
        fun newInstance(): FirstOnboardingFragment = FirstOnboardingFragment()
    }

    override lateinit var viewModel: BaseOnboardingViewModel
    override var isCloseButtonVisible: Boolean = true

    private val binding: FragmentOnboardingContainerBinding by lazyUnsafe {
        FragmentOnboardingContainerBinding.bind(
            requireView()
        )
    }

    override fun getPageLayoutIds(): List<Int> = listOf(
        R.layout.first_slide_layout,
        R.layout.second_slide_layout,
        R.layout.third_slide_layout,
        R.layout.fourth_slide_layout
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = obtainViewModel(FirstOnboardingViewModel.Factory(getPageLayoutIds()))
        initViews()
    }

    private fun initViews() {
        binding.progressBar.max = getPageLayoutIds().size
        setupCancelButton(binding.cancel)
        setupNextButton(binding.next)
    }

    override fun render(viewState: OnboardingViewState) {
        super.render(viewState)

        binding.progressBar.progress = viewState.currentScreenIndex + 1
    }

}
