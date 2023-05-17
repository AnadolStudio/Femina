package com.anadolstudio.femina.base.viewmodel.onboarding

data class OnboardingViewState(
    val screens: List<Screen> = emptyList(),
    val currentScreenIndex: Int = 0,
    val isLastPage: Boolean = false
) {

    companion object {

        fun fromLayoutsIdsList(layoutIds: List<Int>): OnboardingViewState =
            OnboardingViewState(screens = layoutIds.map { Screen(it) })

    }

    data class Screen(
        val layoutId: Int,
        var isScreenViewed: Boolean = false
    )

}
