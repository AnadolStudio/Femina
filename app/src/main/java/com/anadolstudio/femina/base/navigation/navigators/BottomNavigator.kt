package com.anadolstudio.femina.base.navigation.navigators

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.anadolstudio.femina.base.navigation.FixSupportFragmentNavigator
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.ui.calendar.CalendarFragment
import com.anadolstudio.femina.ui.home.HomeFragment
import com.anadolstudio.femina.ui.profile.ProfileFragment
import com.anadolstudio.femina.ui.statistic.StatisticFragment

class BottomNavigator(
        fragmentManager: FragmentManager,
        containerId: Int,
        private val onExit: () -> Unit
) : FixSupportFragmentNavigator(
        fragmentManager,
        containerId
) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment = when (screenKey) {
        Screens.BottomNavigation.PROFILE -> ProfileFragment.newInstance()
        Screens.BottomNavigation.HOME -> HomeFragment.newInstance()
        Screens.BottomNavigation.STATISTIC -> StatisticFragment.newInstance()
        Screens.BottomNavigation.CALENDAR -> CalendarFragment.newInstance()
        else -> throw IllegalArgumentException("Unknown screen $screenKey")
    }

    override fun exit() = onExit.invoke()

}
