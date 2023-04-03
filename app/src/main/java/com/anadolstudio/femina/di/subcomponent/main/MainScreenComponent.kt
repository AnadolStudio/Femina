package com.anadolstudio.femina.di.subcomponent.main

import com.anadolstudio.femina.ui.main.MainFragment
import com.anadolstudio.femina.ui.main.MainViewModel
import dagger.Subcomponent

@MainScreenScope
@Subcomponent(
    modules = [
        MainScreenNavigationModule::class
    ]
)
interface MainScreenComponent {

    @Subcomponent.Builder
    interface Builder {
        fun mainScreenNavigationModule(module: MainScreenNavigationModule): Builder
        fun build(): MainScreenComponent
    }

    fun inject(entry: MainFragment)
    fun inject(entry: MainViewModel.Factory)

}