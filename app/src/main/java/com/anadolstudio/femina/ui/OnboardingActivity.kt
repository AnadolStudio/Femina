package com.anadolstudio.femina.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anadolstudio.femina.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance

class OnboardingActivity : AppIntro() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIndicator()
        setInterfaceColors()

        addSlide(newInstance(R.layout.slide_layout))
        addSlide(newInstance(R.layout.slide_layout))
        addSlide(newInstance(R.layout.slide_layout))
        addSlide(newInstance(R.layout.slide_layout))
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
        val color = R.color.purple_700
        setNextArrowColor(color)
        setColorSkipButton(color)
        setBackArrowColor(color)
        setColorDoneText(color)
    }

    private fun setIndicator() {
        isIndicatorEnabled = true
        setProgressIndicator()
        setIndicatorColor(Color.BLACK, Color.GRAY)
    }

    private fun makeFinalToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}