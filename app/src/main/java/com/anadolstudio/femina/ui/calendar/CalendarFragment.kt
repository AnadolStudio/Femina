package com.anadolstudio.femina.ui.calendar

import android.os.Bundle
import android.view.View
import com.anadolstudio.femina.R
import com.anadolstudio.femina.base.viewmodel.obtainViewModel
import com.anadolstudio.femina.databinding.FragmentCalendarBinding
import com.anadolstudio.femina.extension.dpToPx
import com.anadolstudio.femina.extension.lazyUnsafe
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.yearMonth
import com.kizitonwose.calendar.view.MarginValues
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Locale

class CalendarFragment : BaseFragment(R.layout.fragment_calendar) {

    companion object {
        private val MONTH_BOTTOM_MARGIN = 16.dpToPx()
        private val CALENDAR_START_DATE = LocalDate.of(2014, 1, 1).yearMonth

        fun newInstance(): CalendarFragment = CalendarFragment()
    }

    private lateinit var viewModel: CalendarViewModel
    protected val binding: FragmentCalendarBinding by lazyUnsafe { FragmentCalendarBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(CalendarViewModel.Factory())
        initCalendar()
    }

    private val today = LocalDate.now()
    private var startDate: LocalDate? = null
    private var endDate: LocalDate? = null

    override fun onBackPressedInternal(): Boolean = true.also { viewModel.exit() }

    private fun initCalendar() {
        with(binding.calendarView) {
            dayBinder = object : MonthDayBinder<CalendarDayViewContainer> {
                override fun bind(container: CalendarDayViewContainer, day: CalendarDay) {
                    container.setDate(day, startDate, endDate)
                    container.view.setOnClickListener { onDayClicked(day) }
                }

                override fun create(view: View) = CalendarDayViewContainer(view)
            }
            monthHeaderBinder = object : MonthHeaderFooterBinder<CalendarMonthHeaderViewContainer> {
                override fun bind(container: CalendarMonthHeaderViewContainer, month: CalendarMonth) =
                    container.setMonth(requireContext(), month)

                override fun create(view: View) = CalendarMonthHeaderViewContainer(view)
            }

            val currentMonth = YearMonth.now()
            val firstMonth = CALENDAR_START_DATE
            val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

            setup(firstMonth, currentMonth, firstDayOfWeek)
            scrollToMonth(currentMonth)
            monthMargins = MarginValues(MONTH_BOTTOM_MARGIN, MONTH_BOTTOM_MARGIN, MONTH_BOTTOM_MARGIN, MONTH_BOTTOM_MARGIN)
        }
    }

    /*
      Можно выбирать дни только до сегодняшней даты включительно.
      day.owner == DayOwner.THIS_MONTH необходимо, чтобы можно было выбрать только дни текущего месяца
      Например: 30 31 1 2 3 4 5. Так заканичвается один месяц и начинается следующий (Отображаются 2 таких строки)
      30 и 31 из прошлого месяца, и они не должны нажиматься в рамках отображения следующего месяца,
      т.к. эти дни не отображаются, но все еще есть возможность их нажать. Аналогично для 1-5 в рамках отображения прошлого месяца.
   */
    private fun onDayClicked(day: CalendarDay) {
        // TODO говнокод
        if (!day.date.isAfter(today) && day.position == DayPosition.MonthDate) {
            val date = day.date
            if (startDate != null) {
                if (endDate != null) {
                    // сброс периода кликом на новый день
                    startDate = date
                    endDate = null
                } else if (date < startDate) {
                    // если выбрана дата раньше даты старта периода, меняем их местами
                    val tempDate = startDate
                    startDate = date
                    endDate = tempDate
                } else if (date != startDate) {
                    // установка окончания периода
                    endDate = date
                }
            } else {
                startDate = date
            }

            binding.calendarView.notifyCalendarChanged()
        }
    }
}
