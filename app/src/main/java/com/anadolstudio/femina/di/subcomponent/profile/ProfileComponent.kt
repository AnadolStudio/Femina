package com.anadolstudio.femina.di.subcomponent.profile

import com.anadolstudio.femina.ui.profile.ProfileContainerFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [
    ProfileNavigationModule::class
])
interface ProfileComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProfileComponent
    }

    fun inject(entry: ProfileContainerFragment)

}