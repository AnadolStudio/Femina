package com.anadolstudio.femina.ui.base.fragment

import android.view.View
import com.anadolstudio.femina.ui.main.MenuItem

interface BottomNavigationFragment {

    fun navigateToItem(menuItem: MenuItem)

    fun getMenuItemView(menuItem: MenuItem): View?

}
