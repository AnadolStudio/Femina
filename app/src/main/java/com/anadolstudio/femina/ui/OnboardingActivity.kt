package com.anadolstudio.femina.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class OnboardingActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            AppIntroFragment.createInstance(
            title = "Menstrual Calendar",
            description = "Simple and convenient calendar for period tracking"
        ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "Menstrual Calendar",
                description = "Track your period and chronicle your daily symptoms"
            ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "Menstrual Calendar",
                description = "Gather your statistics and improve the quality of your life!"
            ))
        addSlide(AppIntroFragment.createInstance(
            title = "Lunar Calendar",
            description = "Track the current moon phase and plan your day"
        ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
        TODO("Transition to a next activity")
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
        TODO("Transition to a next activity")
    }
}