package com.anadolstudio.femina.base.fragment.state_util.strategy.base

import com.anadolstudio.femina.base.fragment.state_util.State

interface StateChangeStrategy<T : Enum<T>> {

    fun onStateEnter(state: State<T>, prevState: State<T>?) {}

    fun onStateExit(state: State<T>, nextState: State<T>?) {}
}
