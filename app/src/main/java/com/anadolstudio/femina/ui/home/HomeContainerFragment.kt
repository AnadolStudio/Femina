package com.anadolstudio.femina.ui.home

import android.os.Bundle
import com.anadolstudio.femina.R
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.backpress.OnBackPressable
import com.anadolstudio.femina.base.navigation.navigators.BottomNavigator
import com.anadolstudio.femina.base.navigation.router.BottomNavigationRouter
import com.anadolstudio.femina.di.DI
import com.anadolstudio.femina.ui.base.fragment.BaseFragmentContainer
import com.anadolstudio.femina.ui.statistic.StatisticContainerFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class HomeContainerFragment : BaseFragmentContainer(R.layout.fragment_home_container) {

    companion object {
        fun newInstance() = HomeContainerFragment()
    }

    @field:[Inject Named(Screens.BottomNavigation.HOME_SECTION_QUALIFIER)]
    internal lateinit var router: BottomNavigationRouter

    @field:[Inject Named(Screens.BottomNavigation.HOME_SECTION_QUALIFIER)]
    internal lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        BottomNavigator(
                fragmentManager = childFragmentManager,
                containerId = R.id.fragment_home_container_screens,
                onExit = { (parentFragment as OnBackPressable).performOnBackPressed() }
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.fragment_home_container_screens) == null) {
            router.replaceScreen(getDefaultScreen())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.app.homeScreen.provideComponent().inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun getRouter(): Router = router

    override fun onBackPressedInternal(): Boolean {
        router.exit()

        return true
    }

    override fun getDefaultScreen(): String = Screens.BottomNavigation.HOME
}