package com.anadolstudio.femina.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anadolstudio.femina.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class OnboardingActivity : AppIntro() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIndicator()
        setInterfaceColors()

        addOnboardingSlide("Menstrual Calendar", "Simple and convenient calendar for period tracking")
        addOnboardingSlide("Menstrual Calendar", "Track your period and chronicle your daily symptoms")
        addOnboardingSlide("Menstrual Calendar", "Gather your statistics and improve the quality of your life!")
        addOnboardingSlide("Lunar Calendar", "Track the current moon phase and plan your day")
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

    private fun addOnboardingSlide(title: String, description: String) {
        addSlide(AppIntroFragment.createInstance(
            title = title,
            description = description,
            titleColorRes = R.color.purple_700,
            descriptionColorRes = R.color.black,
        ))
    }

    private fun setInterfaceColors() {
        val color = getThemeColor(androidx.appcompat.R.attr.colorPrimary)
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

    private fun getThemeColor(resId: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(resId, typedValue, true)
        return typedValue.data
    }
}