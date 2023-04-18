package com.anadolstudio.femina.ui.calendar

import android.os.Bundle
import android.view.View
import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.anadolstudio.femina.utils.viewmodel.obtainViewModel

class CalendarFragment : BaseFragment(R.layout.fragment_calendar) {

    companion object {
        fun newInstance(): CalendarFragment = CalendarFragment()
    }

    private lateinit var viewModel: CalendarViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(CalendarViewModel.Factory())
    }

    override fun onBackPressedInternal(): Boolean = true.also { viewModel.exit() }
}
