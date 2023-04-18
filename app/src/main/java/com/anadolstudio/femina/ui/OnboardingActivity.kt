package com.anadolstudio.femina.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anadolstudio.femina.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment

class OnboardingActivity : AppIntro() {

    override val layoutId = R.layout.custom_intro_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpProgressBar()
        setInterfaceColors()

        addOnboardingSlide(R.layout.first_slide_layout)
        addOnboardingSlide(R.layout.second_slide_layout)
        addOnboardingSlide(R.layout.third_slide_layout)
        addOnboardingSlide(R.layout.fourth_slide_layout)
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
        makeFinalToast("Onboarding skipped")
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
        makeFinalToast("Onboarding done")
    }

    private fun setInterfaceColors() {
        val purple = R.color.purple_700
        setBackArrowColor(purple)

        setIndicatorColor(purple, Color.GRAY)
    }

    private fun setUpProgressBar() {
        isIndicatorEnabled = true
        setProgressIndicator()
    }

    private fun addOnboardingSlide(layoutResId: Int) {
        addSlide(AppIntroCustomLayoutFragment.Companion.newInstance(layoutResId))
    }

    private fun makeFinalToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}