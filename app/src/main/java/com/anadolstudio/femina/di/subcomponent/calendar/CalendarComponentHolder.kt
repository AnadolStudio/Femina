package com.anadolstudio.femina.di.subcomponent.calendar

import com.anadolstudio.femina.base.di.SingleComponentHolder
import com.anadolstudio.femina.di.AppComponent
import com.anadolstudio.femina.di.AppComponentHolder

class CalendarComponentHolder(
        userComponentHolder: AppComponentHolder
) : SingleComponentHolder<AppComponent, CalendarComponent>(userComponentHolder) {

    override fun provideInternal(parentComponent: AppComponent): CalendarComponent = parentComponent
            .calendarBuilder()
            .build()

}

