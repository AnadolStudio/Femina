package com.anadolstudio.femina.base.fragment.state_util.strategy

import android.view.View
import com.anadolstudio.femina.base.fragment.state_util.strategy.base.AbstractStateChangeStrategy
import com.anadolstudio.femina.extension.makeGone

open class ShowOnEnterGoneOnExitStrategy<T : Enum<T>> : AbstractStateChangeStrategy<T>() {

    override fun viewOnStateExit(view: View) = view.makeGone()

}
