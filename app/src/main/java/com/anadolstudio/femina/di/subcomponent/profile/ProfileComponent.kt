package com.anadolstudio.femina.di.subcomponent.profile

import com.anadolstudio.femina.ui.profile.ProfileContainerFragment
import com.anadolstudio.femina.ui.profile.ProfileFragment
import com.anadolstudio.femina.ui.profile.ProfileViewModel
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
    fun inject(entry: ProfileViewModel.Factory)

}
