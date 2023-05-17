package com.anadolstudio.femina.base.viewmodel.onboarding

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anadolstudio.femina.base.livedata.onNext
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.RootRouter
import com.anadolstudio.femina.base.viewmodel.SimpleViewModel
import com.anadolstudio.femina.di.DI
import javax.inject.Inject
import javax.inject.Named

abstract class BaseOnboardingViewModel(
    protected val router: RootRouter,
    layoutIds: List<Int>
) : SimpleViewModel<OnboardingViewState>() {

    protected var viewState: OnboardingViewState = OnboardingViewState.fromLayoutsIdsList(layoutIds = layoutIds)
        set(value) {
            field = value
            _state.onNext(value)
        }

    abstract fun onOnboardingFinished()

    open fun onScreenViewed(screenIndex: Int) = Unit

    open fun updateStatusBar(screenIndex: Int, context: Context) = Unit

    open fun onCancel() {
        router.newRootScreen(Screens.Root.MAIN)
    }

    fun startOnboarding(context: Context) {
        openScreen(screenIndex = 0, context)
    }

    fun onBackPressed(currentScreenIndex: Int, context: Context) {
        if (currentScreenIndex > 0) {
            openScreen(screenIndex = currentScreenIndex - 1, context)
        } else {
            router.exit()
        }
    }

    fun onNextPressed(currentScreenIndex: Int, context: Context) {
        val newScreenIndex = currentScreenIndex + 1

        if (newScreenIndex < viewState.screens.size) {
            openScreen(screenIndex = newScreenIndex, context)
        } else {
            onOnboardingFinished()
        }
    }

    fun onScreenSwiped(newScreenIndex: Int, context: Context) {
        openScreen(screenIndex = newScreenIndex, context)
    }

    private fun openScreen(screenIndex: Int, context: Context) {
        val screenInfo = viewState.screens[screenIndex]

        if (!screenInfo.isScreenViewed) {
            screenInfo.isScreenViewed = true
            onScreenViewed(screenIndex)
        }

        updateStatusBar(screenIndex, context)

        viewState = viewState.copy(
            currentScreenIndex = screenIndex,
            isLastPage = screenIndex == viewState.screens.size - 1
        )
    }

}
