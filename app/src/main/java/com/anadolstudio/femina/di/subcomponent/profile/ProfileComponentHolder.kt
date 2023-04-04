package com.anadolstudio.femina.di.subcomponent.profile

import com.anadolstudio.femina.base.di.SingleComponentHolder
import com.anadolstudio.femina.di.AppComponent
import com.anadolstudio.femina.di.AppComponentHolder

class ProfileComponentHolder(
        userComponentHolder: AppComponentHolder
) : SingleComponentHolder<AppComponent, ProfileComponent>(userComponentHolder) {

    override fun provideInternal(parentComponent: AppComponent): ProfileComponent = parentComponent
            .profileBuilder()
            .build()

}

