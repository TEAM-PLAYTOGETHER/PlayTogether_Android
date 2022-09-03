package com.playtogether_android.app.presentation.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentHomeDialogBinding
import com.playtogether_android.app.presentation.base.BaseBottomDialogFragment
import com.playtogether_android.app.presentation.ui.home.adapter.HomeDialogAdapter
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.onboarding.OnboardingReDownLoadActivity
import com.playtogether_android.app.presentation.ui.onboarding.SelectOnboardingActivity
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeFragmentDialog :
    BaseBottomDialogFragment<FragmentHomeDialogBinding>(R.layout.fragment_home_dialog) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var dialogAdapter: HomeDialogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        initDialogSetting()
        clickListener()
        initAdapter()
    }

    fun setChangeCrew(crewId: Int, name: String) {
        PlayTogetherRepository.crewId = crewId
        homeViewModel.setCrewName(name)
        homeViewModel.getNewThunderList(crewId)
        homeViewModel.getHotThunderList(crewId)
        dismiss()
    }

    private fun clickListener() {
        binding.ivHomedialogAddCrew.setOnClickListener {
            // TODO: 동아리 추가하기 화면 이동 intent이후 dismiss
            Intent(requireActivity(), SelectOnboardingActivity::class.java).apply {
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun initDialogSetting() {
        binding.lifecycleOwner = this
        binding.clHomedialogTopContainer.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.94).roundToInt()
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
        dialog?.setCanceledOnTouchOutside(true)
    }

    private fun initAdapter() {
        dialogAdapter = HomeDialogAdapter(this)
        homeViewModel.crewList.observe(viewLifecycleOwner) {
            dialogAdapter.submitList(it)
        }
        binding.rvHomedialogContainer.adapter = dialogAdapter
    }
}