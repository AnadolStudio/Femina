package com.anadolstudio.femina.base.navigation.backpress

interface OnBackPressable {

    fun addOnBackPressedListener(listener: OnBackPressedListener)

    fun removeOnBackPressedListener(listener: OnBackPressedListener)

    fun performOnBackPressed()
}