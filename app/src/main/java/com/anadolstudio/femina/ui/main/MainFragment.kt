package com.anadolstudio.femina.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.anadolstudio.femina.R
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.navigators.MenuNavigator
import com.anadolstudio.femina.databinding.FragmentMainBinding
import com.anadolstudio.femina.di.DI
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.anadolstudio.femina.ui.base.fragment.BottomNavigationFragment
import com.anadolstudio.femina.ui.ui_data.MenuItem
import com.anadolstudio.femina.utils.viewmodel.observe
import com.anadolstudio.femina.utils.viewmodel.obtainViewModel
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Named

class MainFragment : BaseFragment(R.layout.fragment_main), BottomNavigationFragment {

    @field:[Inject Named(Screens.BottomNavigation.MENU_QUALIFIER)]
    internal lateinit var bottomNavigatorHolder: NavigatorHolder

    companion object {
        private const val TITLE_TEXT_SIZE = 10f

        fun newInstance(): MainFragment = MainFragment()
    }

    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.bind(requireView()) }

    private val navigator: Navigator by lazy {
        MenuNavigator(requireActivity(), childFragmentManager, R.id.fragment_main_container)
    }

    private val bottomMenuItems = listOf(
        MenuItem.CALENDAR,
        MenuItem.HOME,
        MenuItem.STATISTIC,
        MenuItem.PROFILE
    )

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.app.mainScreen.provideComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = obtainViewModel(MainViewModel.Factory())

        initViews()

        observe(viewModel.state, this::setCurrentItem)
    }

    private fun setCurrentItem(menuItem: MenuItem) {
        binding.fragmentMainBottomNavigation.setCurrentItem(bottomMenuItems.indexOf(menuItem), false)
    }

    override fun onResume() {
        super.onResume()
        bottomNavigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        bottomNavigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun navigateToItem(menuItem: MenuItem) {
        viewModel.onBottomMenuItemSelected(menuItem)
    }

    override fun getMenuItemView(menuItem: MenuItem): View? =
        binding.fragmentMainBottomNavigation.getViewAtPosition(bottomMenuItems.indexOf(menuItem))

    override fun onBackPressedInternal(): Boolean = true.also { viewModel.exit() }

    private fun initViews() {
        with(binding.fragmentMainBottomNavigation) {

            setTitleTextSizeInSp(TITLE_TEXT_SIZE, TITLE_TEXT_SIZE)

            addItems(bottomMenuItems.map { AHBottomNavigationItem(getString(it.titleId), it.iconId) })

            defaultBackgroundColor = ContextCompat.getColor(context, android.R.color.white)
            inactiveColor = ContextCompat.getColor(context, android.R.color.darker_gray)
            accentColor = ContextCompat.getColor(context, android.R.color.black)
            titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW

            setOnTabSelectedListener { position, _ ->
                val menuItem = MenuItem.values()[position]
                viewModel.onBottomMenuItemSelected(menuItem)

                true
            }
        }
    }
}