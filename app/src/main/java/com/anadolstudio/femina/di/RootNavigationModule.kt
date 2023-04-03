package com.anadolstudio.femina.di

import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.RootRouter
import com.anadolstudio.femina.di.AppScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class RootNavigationModule {

    private val cicerone: Cicerone<RootRouter> = Cicerone.create(RootRouter())

    @Named(Screens.Root.QUALIFIER)
    @Provides
    @AppScope
    internal fun provideRouter(): RootRouter = cicerone.router

    @Named(Screens.Root.QUALIFIER)
    @Provides
    @AppScope
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
