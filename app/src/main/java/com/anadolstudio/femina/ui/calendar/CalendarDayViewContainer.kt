package com.anadolstudio.femina.ui.calendar

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.anadolstudio.femina.R
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.ViewContainer
import java.time.LocalDate

class CalendarDayViewContainer(view: View) : ViewContainer(view) {

    private val dayText: TextView = view.findViewById(R.id.calendar_day_layout_day_text)
    private val todayDot: ImageView = view.findViewById(R.id.calendar_day_layout_today_dot)

    private val today = LocalDate.now()

    fun setDate(day: CalendarDay, startDate: LocalDate?, endDate: LocalDate?) {
        dayText.isInvisible = day.position != DayPosition.MonthDate
        if (day.position != DayPosition.MonthDate) {
            return
        }

        dayText.text = day.date.dayOfMonth.toString()
        todayDot.isVisible = day.date == today

        val (textColor, backgroundColor) = getTextAndBgColor(day, startDate, endDate)

        dayText.setTextColor(textColor)
        todayDot.imageTintList = ColorStateList.valueOf(textColor)
        dayText.backgroundTintList = ColorStateList.valueOf(backgroundColor)
    }

    private fun getTextAndBgColor(day: CalendarDay, startDate: LocalDate?, endDate: LocalDate?) = when {
        isSelectedDate(day.date, startDate, endDate) -> {
            Color.WHITE to view.context.getColor(R.color.accentColor)
        }
        day.date > today -> {
            view.context.getColor(R.color.light_gray) to view.context.getColor(R.color.light_ui_background)
        }
        else -> {
            Color.BLACK to view.context.getColor(R.color.light_ui_background)
        }
    }

    private fun isSelectedDate(date: LocalDate, startDate: LocalDate?, endDate: LocalDate?): Boolean {
        return when {
            startDate != null && date.isEqual(startDate) -> true
            endDate != null && date.isEqual(endDate) -> true
            startDate != null && endDate != null && date.isAfter(startDate) && date.isBefore(endDate) -> true
            else -> false
        }
    }
}
