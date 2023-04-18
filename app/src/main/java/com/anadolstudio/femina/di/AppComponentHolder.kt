package com.anadolstudio.femina.di

import android.content.Context
import com.anadolstudio.femina.base.di.RootComponentHolder
import com.anadolstudio.femina.di.subcomponent.calendar.CalendarComponentHolder
import com.anadolstudio.femina.di.subcomponent.home.HomeComponentHolder
import com.anadolstudio.femina.di.subcomponent.main.MainScreenComponentHolder
import com.anadolstudio.femina.di.subcomponent.profile.ProfileComponentHolder
import com.anadolstudio.femina.di.subcomponent.statistic.StatisticComponentHolder

class AppComponentHolder(private val context: Context) : RootComponentHolder<AppComponent>() {

    val mainScreen: MainScreenComponentHolder by lazy { MainScreenComponentHolder(this) }
    val homeScreen: HomeComponentHolder by lazy { HomeComponentHolder(this) }
    val statisticScreen: StatisticComponentHolder by lazy { StatisticComponentHolder(this) }
    val calendarScreen: CalendarComponentHolder by lazy { CalendarComponentHolder(this) }
    val profileScreen: ProfileComponentHolder by lazy { ProfileComponentHolder(this) }

    override fun provideInternal(): AppComponent {
        return DaggerAppComponent
                .builder()
                .appContext(context)
                .build()
    }

    override fun onComponentDestroyed() {
        mainScreen.destroyComponent()
        homeScreen.destroyComponent()
        profileScreen.destroyComponent()
        calendarScreen.destroyComponent()
        statisticScreen.destroyComponent()
    }
}

