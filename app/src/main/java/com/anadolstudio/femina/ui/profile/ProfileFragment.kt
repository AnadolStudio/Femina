package com.anadolstudio.femina.ui.profile

import android.os.Bundle
import android.view.View
import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.anadolstudio.femina.base.viewmodel.obtainViewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(ProfileViewModel.Factory())
    }

    override fun onBackPressedInternal(): Boolean = true.also { viewModel.exit() }
}
