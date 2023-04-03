package com.anadolstudio.femina.di.subcomponent.statistic

import com.anadolstudio.femina.base.di.SingleComponentHolder
import com.anadolstudio.femina.di.AppComponent
import com.anadolstudio.femina.di.AppComponentHolder

class StatisticComponentHolder(
        userComponentHolder: AppComponentHolder
) : SingleComponentHolder<AppComponent, StatisticComponent>(userComponentHolder) {

    override fun provideInternal(parentComponent: AppComponent): StatisticComponent = parentComponent
            .statisticBuilder()
            .build()

}

