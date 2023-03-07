package com.anadolstudio.femina.base.fragment

import androidx.annotation.LayoutRes
import com.anadolstudio.femina.base.event.SingleNavigationEvent
import com.anadolstudio.femina.base.livedata.SingleEvent
import com.anadolstudio.femina.utils.viewmodel.BaseViewModel

abstract class BaseFragment<ViewState, ViewModel: BaseViewModel<ViewState>>(
        @LayoutRes layoutId: Int
) : CoreBaseFragment<ViewState, ViewModel>(layoutId) {

    override fun handleEvent(event: SingleEvent) = when (event) {
        is SingleNavigationEvent -> Unit
        else -> super.handleEvent(event)
    }

    // TODO
    open fun handleNavigationEvent(event: SingleNavigationEvent) = Unit
}
