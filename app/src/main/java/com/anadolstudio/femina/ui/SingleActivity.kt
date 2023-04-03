package com.anadolstudio.femina.ui

import android.os.Bundle
import com.anadolstudio.femina.R
import com.anadolstudio.femina.base.event.SingleErrorDialog
import com.anadolstudio.femina.base.event.SingleMessageDialog
import com.anadolstudio.femina.base.livedata.SingleError
import com.anadolstudio.femina.base.livedata.SingleEvent
import com.anadolstudio.femina.base.livedata.SingleMessage
import com.anadolstudio.femina.base.navigation.Screens
import com.anadolstudio.femina.base.navigation.navigators.RootNavigator
import com.anadolstudio.femina.di.DI
import com.anadolstudio.femina.ui.base.activity.BaseActivity
import com.anadolstudio.femina.utils.viewmodel.obtainViewModel
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Named

class SingleActivity : BaseActivity() {

    @field:[Inject Named(Screens.Root.QUALIFIER)]
    internal lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        RootNavigator(this, R.id.activity_start_container_screens)
    }

    private lateinit var viewModel: SingleViewModel

    override fun defaultErrorMessage(): String {
        TODO("Not yet implemented")
    }

    override fun showError(event: SingleError) {
        TODO("Not yet implemented")
    }

    override fun showMessage(event: SingleMessage) {
        TODO("Not yet implemented")
    }

    override fun showMessageDialog(event: SingleMessageDialog) {
        TODO("Not yet implemented")
    }

    override fun showErrorDialog(event: SingleErrorDialog) {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: SingleEvent) {
        TODO("Not yet implemented")
    }

    override fun onBackPressedInternal(): Boolean {
        finish()

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppTheme) // TODO
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        DI.app.provideComponent().inject(this)

        viewModel = obtainViewModel(SingleViewModel.Factory())

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        DI.app.onDependencyReleased()
        super.onDestroy()
    }

}
