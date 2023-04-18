package com.anadolstudio.femina.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.anadolstudio.femina.base.livedata.SingleError
import com.anadolstudio.femina.base.livedata.SingleEvent
import com.anadolstudio.femina.base.livedata.SingleMessage
import com.anadolstudio.femina.base.navigation.backpress.OnBackPressable
import com.anadolstudio.femina.base.navigation.backpress.OnBackPressedListener

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(), OnBackPressable, OnBackPressedListener {

    /**
     * Делает иконки статус бара темными.
     * Необходимо устанавливать true (значение по умолчанию) для статус бара с светлым цветом.
     * Для темных цветов нужно устанавливать false.
     */

    protected open var isDarkStatusBarIcons: Boolean = true

    private var childBackPressedListeners: MutableSet<OnBackPressedListener> = LinkedHashSet()

    abstract fun onBackPressedInternal(): Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Исправление проблемы с fitsSystemWindow.
         * При использовании fragments иногда происходит ошибка в расчётах fitsSystemWindow.
         * Эта строчка вызывает перерасчёт insets.
         *
         * Подробности тут: https://medium.com/androiddevelopers/windows-insets-fragment-transitions-9024b239a436
         */
        ViewCompat.requestApplyInsets(view)
    }

    override fun onResume() {
        super.onResume()
        // Работать со статус баром нужно в onResume, чтобы статус бар обновлялся при возвращении на экран.
        updateStatusBarIcons()

        getParentOnBackPressable().addOnBackPressedListener(this)
    }

    open fun handleEvent(event: SingleEvent) = when (event) { // TODO
        is SingleMessage -> Unit
        is SingleError -> Unit
        else -> Unit
    }

    override fun onPause() {
        getParentOnBackPressable().removeOnBackPressedListener(this)
        super.onPause()
    }

    override fun addOnBackPressedListener(listener: OnBackPressedListener) {
        childBackPressedListeners.add(listener)
    }

    override fun removeOnBackPressedListener(listener: OnBackPressedListener) {
        childBackPressedListeners.remove(listener)
    }

    override fun performOnBackPressed() {
        onBackPressedInternal()
    }

    final override fun onBackPressed(): Boolean = when {
        childBackPressedListeners.any { it.onBackPressed() } -> true
        else -> onBackPressedInternal()
    }

    // TODO fix deprecated code
    protected fun setDarkStatusBarIcon() {
        val currentFlags = requireActivity().window.decorView.systemUiVisibility
        requireActivity().window.decorView.systemUiVisibility = currentFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    protected fun setLightStatusBarIcon() {
        val currentFlags = requireActivity().window.decorView.systemUiVisibility
        requireActivity().window.decorView.systemUiVisibility = currentFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }

    protected fun updateStatusBarIcons() {
        if (isDarkStatusBarIcons) {
            setDarkStatusBarIcon()
        } else {
            setLightStatusBarIcon()
        }
    }

    private fun getParentOnBackPressable(): OnBackPressable = if (parentFragment != null) {
        parentFragment as OnBackPressable
    } else {
        activity as OnBackPressable
    }

}
