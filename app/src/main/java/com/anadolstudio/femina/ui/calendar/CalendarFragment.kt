package com.anadolstudio.femina.ui.calendar

import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment

class CalendarFragment : BaseFragment(R.layout.fragment_calendar) {

    companion object{
        fun newInstance(): CalendarFragment = CalendarFragment()
    }

    override fun onBackPressedInternal(): Boolean {
        TODO("Not yet implemented")
    }
}