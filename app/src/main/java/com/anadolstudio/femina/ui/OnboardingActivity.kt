package com.anadolstudio.femina.ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.anadolstudio.femina.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment

class OnboardingActivity : AppIntro() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpOnboardingMenu()
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
        val color = R.color.purple_700
        setNextArrowColor(color)
        setColorSkipButton(color)
        setBackArrowColor(color)
        setColorDoneText(color)

        setIndicatorColor(R.color.purple_700, Color.GRAY)
    }

    private fun setUpOnboardingMenu() {
        isIndicatorEnabled = true
        setProgressIndicator()

        val progressBar = findViewById<ViewGroup>(com.github.appintro.R.id.indicator_container)
        val layoutParams = progressBar.layoutParams
        layoutParams.width = 500
        progressBar.layoutParams = layoutParams


        val skipButton = findViewById<Button>(com.github.appintro.R.id.skip)
        val drawable: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.button_outline, null)
        skipButton.background = drawable
    }

    private fun addOnboardingSlide(layoutResId: Int) {
        addSlide(AppIntroCustomLayoutFragment.Companion.newInstance(layoutResId))
    }

    private fun makeFinalToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}