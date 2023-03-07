package com.anadolstudio.femina.base.fragment.state_util

import com.anadolstudio.femina.extension.makeGone

class AllHideInitialState<T : Enum<T>> : InitialState<T> {

    override fun apply(states: Array<out State<T>>) = states.forEach { state ->
        state.viewsGroup.forEach { it.makeGone() }
    }
}
