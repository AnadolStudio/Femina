package com.anadolstudio.femina.di.subcomponent.calendar

import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class CalendarNavigationModule {

    private val cicerone: Cicerone<BottomNavigationRouter> = Cicerone.create(BottomNavigationRouter())

    @Named(Screens.BottomNavigation.CALENDAR_SECTION_QUALIFIER)
    @Provides
    @CalendarScope
    internal fun provideRouter(): BottomNavigationRouter = cicerone.router

    @Named(Screens.BottomNavigation.CALENDAR_SECTION_QUALIFIER)
    @Provides
    @CalendarScope
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
