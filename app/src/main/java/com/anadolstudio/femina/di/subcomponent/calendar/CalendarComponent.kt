package com.anadolstudio.femina.di.subcomponent.calendar

import com.anadolstudio.femina.ui.calendar.CalendarContainerFragment
import com.anadolstudio.femina.ui.calendar.CalendarViewModel
import dagger.Subcomponent

@CalendarScope
@Subcomponent(modules = [
    CalendarNavigationModule::class
])
interface CalendarComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): CalendarComponent
    }

    fun inject(entry: CalendarContainerFragment)
    fun inject(entry: CalendarViewModel.Factory)

}
