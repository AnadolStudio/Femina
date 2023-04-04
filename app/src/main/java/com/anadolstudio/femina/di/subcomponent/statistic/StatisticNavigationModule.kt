package com.anadolstudio.femina.di.subcomponent.statistic

import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class StatisticNavigationModule {

    private val cicerone: Cicerone<BottomNavigationRouter> = Cicerone.create(BottomNavigationRouter())

    @Named(Screens.BottomNavigation.STATISTIC_SECTION_QUALIFIER)
    @Provides
    @StatisticScope
    internal fun provideRouter(): BottomNavigationRouter = cicerone.router

    @Named(Screens.BottomNavigation.STATISTIC_SECTION_QUALIFIER)
    @Provides
    @StatisticScope
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
