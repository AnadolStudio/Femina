package com.anadolstudio.femina.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anadolstudio.femina.base.livedata.onNext
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import com.anadolstudio.femina.base.navigation.router.RootRouter
import com.anadolstudio.femina.di.DI
import com.anadolstudio.femina.ui.ui_data.MenuItem
import com.anadolstudio.femina.utils.viewmodel.SimpleViewModel
import javax.inject.Inject
import javax.inject.Named

class MainViewModel(
    private val context: Context,
    private val router: BottomNavigationRouter,
    private val rootRouter: RootRouter,
) : SimpleViewModel<MenuItem>() {

    init {
        _state.value?.let { updateMenuItem(MenuItem.HOME) }
    }

    private fun updateMenuItem(menuItem: MenuItem, isBottomMenuItemClick: Boolean = false) {
        if (_state.value == menuItem && isBottomMenuItemClick) {
            router.replaceAndResetScreen(getScreenByMenuItem(menuItem), true)
        } else {
            router.replaceScreen(getScreenByMenuItem(menuItem), isBottomMenuItemClick)
        }
        _state.onNext(menuItem)
    }

    private fun getScreenByMenuItem(menuItem: MenuItem): String = when (menuItem) {
        MenuItem.HOME -> Screens.BottomNavigation.HOME_SECTION
        MenuItem.STATISTIC -> Screens.BottomNavigation.STATISTIC_SECTION
        MenuItem.CALENDAR -> Screens.BottomNavigation.CALENDAR_SECTION
        MenuItem.PROFILE -> Screens.BottomNavigation.PROFILE_SECTION
    }

    fun onBottomMenuItemSelected(menuItem: MenuItem) {
        updateMenuItem(
            menuItem = menuItem,
            isBottomMenuItemClick = true
        )
    }

    fun exit() {
        if (_state.value != MenuItem.HOME) {
            updateMenuItem(MenuItem.HOME)
        } else {
            router.exit()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.NewInstanceFactory() {

        @Inject
        lateinit var context: Context

        @field:[Inject Named(Screens.BottomNavigation.MENU_QUALIFIER)]
        internal lateinit var router: BottomNavigationRouter

        @field:[Inject Named(Screens.Root.QUALIFIER)]
        internal lateinit var rootRouter: RootRouter

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            DI.app.mainScreen.provideComponent().inject(this)

            return MainViewModel(
                context = context,
                router = router,
                rootRouter = rootRouter
            ) as T
        }
    }

}