package com.anadolstudio.femina.ui.profile

import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    companion object{
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    override fun onBackPressedInternal(): Boolean {
        TODO("Not yet implemented")
    }
}