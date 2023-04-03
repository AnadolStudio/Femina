package com.anadolstudio.femina.ui.base.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.anadolstudio.femina.base.activity.CoreBaseSingleActivity
import com.anadolstudio.femina.base.navigation.backpress.OnBackPressable
import com.anadolstudio.femina.base.navigation.backpress.OnBackPressedListener

abstract class BaseActivity : CoreBaseSingleActivity(), OnBackPressable {

    private var childBackPressedListeners: MutableSet<OnBackPressedListener> = LinkedHashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
    }

    abstract fun onBackPressedInternal(): Boolean

    override fun addOnBackPressedListener(listener: OnBackPressedListener) {
        childBackPressedListeners.add(listener)
    }

    override fun removeOnBackPressedListener(listener: OnBackPressedListener) {
        childBackPressedListeners.remove(listener)
    }

    final override fun onBackPressed() {
        val isConsumed = childBackPressedListeners.any { it.onBackPressed() }

        if (!isConsumed) {
            onBackPressedInternal()
        }
    }

    override fun performOnBackPressed() {
        onBackPressedInternal()
    }

    private fun setTransparentStatusBar() {
        window.apply {
            decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

}
