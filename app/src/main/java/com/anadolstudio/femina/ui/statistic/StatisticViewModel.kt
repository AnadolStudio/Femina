package com.anadolstudio.femina.ui.statistic

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import com.anadolstudio.femina.di.DI
import com.anadolstudio.femina.ui.main.MenuItem
import com.anadolstudio.femina.utils.viewmodel.SimpleViewModel
import javax.inject.Inject
import javax.inject.Named

class StatisticViewModel(
        private val router: BottomNavigationRouter,
) : SimpleViewModel<MenuItem>() {

    fun exit() {
        router.exit()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.NewInstanceFactory() {

        @Inject
        lateinit var context: Context

        @field:[Inject Named(Screens.BottomNavigation.STATISTIC_SECTION_QUALIFIER)]
        internal lateinit var router: BottomNavigationRouter

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            DI.app.statisticScreen.provideComponent().inject(this)

            return StatisticViewModel(
                    router = router,
            ) as T
        }
    }

}
