package com.anadolstudio.femina.di.subcomponent.home

import com.anadolstudio.femina.base.di.SingleComponentHolder
import com.anadolstudio.femina.di.AppComponent
import com.anadolstudio.femina.di.AppComponentHolder

class HomeComponentHolder(
        userComponentHolder: AppComponentHolder
) : SingleComponentHolder<AppComponent, HomeComponent>(userComponentHolder) {

    override fun provideInternal(parentComponent: AppComponent): HomeComponent = parentComponent
            .homeBuilder()
            .build()

}

