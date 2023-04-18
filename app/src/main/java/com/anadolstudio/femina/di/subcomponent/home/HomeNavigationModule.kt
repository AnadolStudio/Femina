package com.anadolstudio.femina.di.subcomponent.home

import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class HomeNavigationModule {

    private val cicerone: Cicerone<BottomNavigationRouter> = Cicerone.create(BottomNavigationRouter())

    @Named(Screens.BottomNavigation.HOME_SECTION_QUALIFIER)
    @Provides
    @HomeScope
    internal fun provideRouter(): BottomNavigationRouter = cicerone.router

    @Named(Screens.BottomNavigation.HOME_SECTION_QUALIFIER)
    @Provides
    @HomeScope
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
