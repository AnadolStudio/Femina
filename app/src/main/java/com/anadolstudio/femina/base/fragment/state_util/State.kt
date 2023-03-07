package com.anadolstudio.femina.base.fragment.state_util

import android.view.View
import com.anadolstudio.femina.base.fragment.state_util.strategy.ShowOnEnterGoneOnExitStrategy
import com.anadolstudio.femina.base.fragment.state_util.strategy.base.StateChangeStrategy

data class State<T>(
        val name: T,
        val viewsGroup: List<View>,
        val strategy: StateChangeStrategy<T> = ShowOnEnterGoneOnExitStrategy()
) where T : Enum<T>
