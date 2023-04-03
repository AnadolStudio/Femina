package com.anadolstudio.femina.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.RootRouter
import com.anadolstudio.femina.di.DI
import com.anadolstudio.femina.utils.viewmodel.BaseViewModel
import javax.inject.Inject
import javax.inject.Named

class SingleViewModel(
        private val router: RootRouter
) : BaseViewModel() {

    init {
        router.newRootScreen(Screens.Root.MAIN)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.NewInstanceFactory() {

        @field:[Inject Named(Screens.Root.QUALIFIER)]
        internal lateinit var router: RootRouter

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            DI.app.provideComponent().inject(this)

            return SingleViewModel(router) as T
        }
    }

}