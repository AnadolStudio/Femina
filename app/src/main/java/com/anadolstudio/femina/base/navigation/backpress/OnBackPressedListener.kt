package com.anadolstudio.femina.base.navigation.backpress

interface OnBackPressedListener {
    /**
     * @return if back pressed command has processed
     */
    fun onBackPressed(): Boolean
}