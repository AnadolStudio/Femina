package com.anadolstudio.femina.ui.base.fragment

import android.view.View
import com.anadolstudio.femina.ui.ui_data.MenuItem

interface BottomNavigationFragment {

    fun navigateToItem(menuItem: MenuItem)

    fun getMenuItemView(menuItem: MenuItem) : View?

}