package com.anadolstudio.femina.ui.home

import android.os.Bundle
import android.view.View
import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.anadolstudio.femina.base.viewmodel.obtainViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(HomeViewModel.Factory())
    }

    override fun onBackPressedInternal(): Boolean = true.also { viewModel.exit() }
}
