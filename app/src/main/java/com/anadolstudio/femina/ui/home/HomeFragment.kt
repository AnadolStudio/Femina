package com.anadolstudio.femina.ui.home

import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onBackPressedInternal(): Boolean {
        TODO("Not yet implemented")
    }
}