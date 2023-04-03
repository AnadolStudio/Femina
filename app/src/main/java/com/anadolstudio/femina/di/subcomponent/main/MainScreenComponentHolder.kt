package com.anadolstudio.femina.di.subcomponent.main

import com.anadolstudio.femina.base.di.SingleComponentHolder
import com.anadolstudio.femina.di.AppComponent
import com.anadolstudio.femina.di.AppComponentHolder

class MainScreenComponentHolder(
        userComponentHolder: AppComponentHolder
) : SingleComponentHolder<AppComponent, MainScreenComponent>(userComponentHolder) {

    override fun provideInternal(parentComponent: AppComponent): MainScreenComponent = parentComponent
            .mainScreenBuilder()
            .mainScreenNavigationModule(MainScreenNavigationModule())
            .build()

}

