package com.anadolstudio.femina.ui.ui_data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.anadolstudio.femina.R

enum class MenuItem (@DrawableRes val iconId: Int, @StringRes val titleId: Int) {

    HOME(R.drawable.ic_home, R.string.bottom_navigation_home),
    CALENDAR(R.drawable.ic_calendar, R.string.bottom_navigation_calendar),
    STATISTIC(R.drawable.ic_statistic, R.string.bottom_navigation_statistic),
    PROFILE(R.drawable.ic_profile, R.string.bottom_navigation_profile);

}