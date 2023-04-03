package com.anadolstudio.femina.di.subcomponent.main

import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class MainScreenNavigationModule {

    private val cicerone: Cicerone<BottomNavigationRouter> = Cicerone.create(BottomNavigationRouter())

    @Named(Screens.BottomNavigation.MENU_QUALIFIER)
    @Provides
    @MainScreenScope
    internal fun provideRouter(): BottomNavigationRouter = cicerone.router

    @Named(Screens.BottomNavigation.MENU_QUALIFIER)
    @Provides
    @MainScreenScope
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

}
