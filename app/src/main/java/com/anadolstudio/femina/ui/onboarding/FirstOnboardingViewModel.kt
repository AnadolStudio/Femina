package com.anadolstudio.femina.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.RootRouter
import com.anadolstudio.femina.base.viewmodel.onboarding.BaseOnboardingViewModel
import com.anadolstudio.femina.di.DI
import javax.inject.Inject
import javax.inject.Named

class FirstOnboardingViewModel(
    router: RootRouter,
    layoutIds: List<Int>
) : BaseOnboardingViewModel(
    router = router,
    layoutIds = layoutIds
) {

    override fun onOnboardingFinished() {
        router.newRootScreen(Screens.Root.MAIN)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val layoutIds: List<Int>
    ) : ViewModelProvider.NewInstanceFactory() {


        @field:[Inject Named(Screens.Root.QUALIFIER)]
        lateinit var router: RootRouter

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            DI.app.provideComponent().inject(this)

            return FirstOnboardingViewModel(
                router = router,
                layoutIds = layoutIds
            ) as T
        }
    }

}
