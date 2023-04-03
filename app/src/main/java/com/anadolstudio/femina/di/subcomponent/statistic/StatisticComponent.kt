package com.anadolstudio.femina.di.subcomponent.statistic

import com.anadolstudio.femina.ui.statistic.StatisticContainerFragment
import dagger.Subcomponent

@StatisticScope
@Subcomponent(modules = [
    StatisticNavigationModule::class
])
interface StatisticComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): StatisticComponent
    }

    fun inject(entry: StatisticContainerFragment)

}