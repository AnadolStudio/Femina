package com.anadolstudio.femina.base.navigation.navigators

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.command.AddLocalScreen
import com.anadolstudio.femina.base.navigation.command.ReplaceAndReset
import com.anadolstudio.femina.ui.base.fragment.BaseFragmentContainer
import com.anadolstudio.femina.ui.calendar.CalendarContainerFragment
import com.anadolstudio.femina.ui.home.HomeContainerFragment
import com.anadolstudio.femina.ui.profile.ProfileContainerFragment
import com.anadolstudio.femina.ui.statistic.StatisticContainerFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace

class MenuNavigator(
        private val activity: FragmentActivity,
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) : Navigator {

    private var calendarContainerFragment: CalendarContainerFragment
    private var homeContainerFragment: HomeContainerFragment
    private var statisticContainerFragment: StatisticContainerFragment
    private var profileContainerFragment: ProfileContainerFragment

    init {
        calendarContainerFragment = initContainer(Screens.BottomNavigation.CALENDAR_SECTION) { CalendarContainerFragment.newInstance() }
        homeContainerFragment = initContainer(Screens.BottomNavigation.HOME_SECTION) { HomeContainerFragment.newInstance() }
        statisticContainerFragment = initContainer(Screens.BottomNavigation.STATISTIC_SECTION) { StatisticContainerFragment.newInstance() }
        profileContainerFragment = initContainer(Screens.BottomNavigation.PROFILE_SECTION) { ProfileContainerFragment.newInstance() }
    }

    private fun getMenuFragmentList(withOut: BaseFragmentContainer): List<Fragment> = listOf(
            calendarContainerFragment,
            homeContainerFragment,
            statisticContainerFragment,
            profileContainerFragment
    ).filter { it != withOut }

    override fun applyCommands(commands: Array<out Command>?) {
        commands?.forEach(this::applyCommand)
    }

    private fun applyCommand(command: Command?) {
        when (command) {
            is Replace -> replaceScreen(command)
            is AddLocalScreen -> addLocalScreen(command)
            is Back -> activity.finish()
            else -> throw IllegalArgumentException("Unsupported command $command")
        }
    }

    private fun replaceScreen(command: Replace) = when (command.screenKey) {
        Screens.BottomNavigation.HOME_SECTION -> replaceScreen(command, homeContainerFragment)
        Screens.BottomNavigation.CALENDAR_SECTION -> replaceScreen(command, calendarContainerFragment)
        Screens.BottomNavigation.STATISTIC_SECTION -> replaceScreen(command, statisticContainerFragment)
        Screens.BottomNavigation.PROFILE_SECTION -> replaceScreen(command, profileContainerFragment)
        else -> throw IllegalArgumentException("Unknown screen ${command.screenKey}")
    }

    private fun replaceScreen(command: Replace, fragment: BaseFragmentContainer) {
        replace(toDetach = getMenuFragmentList(fragment), toAttach = fragment)

        if (command is ReplaceAndReset) fragment.cleanScreenStack()
    }

    private fun addLocalScreen(command: AddLocalScreen) {
        val fragment: BaseFragmentContainer = when (command.rootScreenKey) {
            Screens.BottomNavigation.CALENDAR_SECTION -> calendarContainerFragment
            Screens.BottomNavigation.HOME_SECTION -> homeContainerFragment
            Screens.BottomNavigation.STATISTIC_SECTION -> statisticContainerFragment
            Screens.BottomNavigation.PROFILE_SECTION -> profileContainerFragment
            else -> throw IllegalArgumentException("Unknown screen ${command.rootScreenKey}")
        }

        fragment.getRouter().navigateTo(command.localScreenKey, command.localScreenData)
    }

    @SuppressLint("CommitTransaction")
    private fun replace(toDetach: List<Fragment>, toAttach: Fragment) = with(fragmentManager.beginTransaction()) {
        toDetach.forEach { detach(it) }
        attach(toAttach)
        commitNow()
    }

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("CommitTransaction")
    private fun <T : Fragment> initContainer(tag: String, factory: () -> T): T = with(fragmentManager) {
        var fragment = findFragmentByTag(tag) as T?

        if (fragment == null) {
            fragment = factory.invoke()

            with(beginTransaction()) {
                add(containerId, fragment, tag)
                detach(fragment)
                commitNow()
            }
        }

        return@with fragment
    }
}
