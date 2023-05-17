package com.anadolstudio.femina.di

import android.content.Context
import com.anadolstudio.femina.di.subcomponent.calendar.CalendarComponent
import com.anadolstudio.femina.di.subcomponent.home.HomeComponent
import com.anadolstudio.femina.di.subcomponent.main.MainScreenComponent
import com.anadolstudio.femina.di.subcomponent.profile.ProfileComponent
import com.anadolstudio.femina.di.subcomponent.statistic.StatisticComponent
import com.anadolstudio.femina.ui.SingleActivity
import com.anadolstudio.femina.ui.SingleViewModel
import com.anadolstudio.femina.ui.onboarding.FirstOnboardingViewModel
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
        modules = [
            AppModule::class,
            RootNavigationModule::class,
        ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }

    fun mainScreenBuilder(): MainScreenComponent.Builder
    fun homeBuilder(): HomeComponent.Builder
    fun calendarBuilder(): CalendarComponent.Builder
    fun profileBuilder(): ProfileComponent.Builder
    fun statisticBuilder(): StatisticComponent.Builder

    fun inject(entry: SingleActivity)
    fun inject(entry: SingleViewModel.Factory)

    fun inject(entry: FirstOnboardingViewModel.Factory)

}
