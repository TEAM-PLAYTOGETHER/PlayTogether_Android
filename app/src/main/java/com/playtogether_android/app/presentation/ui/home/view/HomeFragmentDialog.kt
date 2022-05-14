package com.playtogether_android.app.presentation.ui.home.view

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentHomeDialogBinding
import com.playtogether_android.app.presentation.base.BaseBottomDialogFragment
import kotlin.math.roundToInt

class HomeFragmentDialog :
    BaseBottomDialogFragment<FragmentHomeDialogBinding>(R.layout.fragment_home_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
//        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding.clHomedialogTopContainer.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.94).roundToInt()
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = false
            state = BottomSheetBehavior.STATE_HALF_EXPANDED
//            state = BottomSheetBehavior.STATE_EXPANDED
        }
        initAdapter()
        setClickListener()
    }

    private fun setClickListener() {
        binding.ivHomedialogCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initAdapter() {
        //회원이 가입한 동아리 리스트 조회
    }
}