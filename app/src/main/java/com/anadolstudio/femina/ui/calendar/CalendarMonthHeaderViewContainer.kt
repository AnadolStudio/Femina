package com.anadolstudio.femina.ui.calendar

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.anadolstudio.femina.R
import com.anadolstudio.femina.utils.MonthMapper
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.view.ViewContainer
import java.time.format.TextStyle
import java.util.Locale

class CalendarMonthHeaderViewContainer(view: View) : ViewContainer(view) {

    private val monthTitle: TextView = view.findViewById(R.id.calendar_month_header_month_title)
    private val legendContainer: LinearLayout = view.findViewById(R.id.calendar_month_header_legend)

    fun setMonth(context: Context, month: CalendarMonth) {
        val monthText = MonthMapper.monthMap(context, month.yearMonth.month.ordinal)
        val title = StringBuilder(monthText.capitalize())
        title.append(" ${month.yearMonth.month}")
        monthTitle.text = title

        month.weekDays.first().forEachIndexed { index, day ->
            val dayName = day.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            (legendContainer.getChildAt(index) as? TextView)?.text = dayName.toUpperCase()
        }
    }
}
