package com.anadolstudio.femina.base.fragment.state_util.strategy

import android.view.View
import com.anadolstudio.femina.utils.animate.AnimateUtil

class ShimmersGoneStrategy<T : Enum<T>>(vararg viewsWithoutAnim: View) : ShowOnEnterGoneOnExitStrategy<T>() {

    override val viewsWithoutAnim: List<View> = viewsWithoutAnim.toList()
    override val animation = AnimateUtil.blinkAnimation()

}
