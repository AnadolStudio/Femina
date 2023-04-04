package com.anadolstudio.femina.base.navigation.navigators

import androidx.fragment.app.Fragment
import com.anadolstudio.femina.base.navigation.FixSupportFragmentNavigator
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.ui.base.activity.BaseActivity
import com.anadolstudio.femina.ui.main.MainFragment

class RootNavigator(
        private val activity: BaseActivity,
        containerId: Int
) : FixSupportFragmentNavigator(
        activity.supportFragmentManager,
        containerId
) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment = when (screenKey) {
        Screens.Root.MAIN -> MainFragment.newInstance()
        else -> throw IllegalArgumentException("Unknown screen $screenKey")
    }

    override fun exit() {
        activity.finish()
    }
}
