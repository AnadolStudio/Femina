package com.anadolstudio.femina.ui.base.fragment

import android.view.View
import androidx.annotation.LayoutRes
import com.anadolstudio.femina.base.navigation.router.RouterProvider
import com.anadolstudio.femina.ui.main.MenuItem

abstract class BaseFragmentContainer(@LayoutRes private val layoutId: Int) : BaseFragment(layoutId), StackScreenClean, RouterProvider, BottomNavigationFragment {

    override fun navigateToItem(menuItem: MenuItem) {
        (parentFragment as? BottomNavigationFragment)?.navigateToItem(menuItem)
    }

    override fun getMenuItemView(menuItem: MenuItem): View? = (parentFragment as? BottomNavigationFragment)?.getMenuItemView(menuItem)

    override fun cleanScreenStack() {
        if (childFragmentManager.backStackEntryCount > 0) {
            getRouter().backTo(getDefaultScreen())
        }
    }

    abstract fun getDefaultScreen(): String

}
