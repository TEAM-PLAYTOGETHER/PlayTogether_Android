package com.playtogether_android.app.presentation.ui.home.view

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentHomeDialogBinding
import com.playtogether_android.app.presentation.base.BaseBottomDialogFragment
import com.playtogether_android.app.presentation.base.BaseFragment

class HomeFragmentDialog :
    BaseBottomDialogFragment<FragmentHomeDialogBinding>(R.layout.fragment_home_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        initAdapter()
    }

    private fun initAdapter() {

    }
}