package com.anadolstudio.femina.di.subcomponent.home

import com.anadolstudio.femina.ui.home.HomeContainerFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [
    HomeNavigationModule::class
])
interface HomeComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): HomeComponent
    }

    fun inject(entry: HomeContainerFragment)

}