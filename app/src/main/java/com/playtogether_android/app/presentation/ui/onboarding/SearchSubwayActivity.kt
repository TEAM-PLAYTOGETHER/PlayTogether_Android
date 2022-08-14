package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySearchSubwayBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.*
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.onboarding.SubwayListData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SearchSubwayActivity :
    BaseActivity<ActivitySearchSubwayBinding>(R.layout.activity_search_subway){

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private lateinit var subwayAdapter: SubwayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getList()
        backBtnListener()
        nullCheck()
    }
    


    private fun backBtnListener() {
        binding.ivSubwayOnboardingBack.setOnClickListener {
            val intent = Intent(this, OnBoardingIntroduceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initTextFieldCheck() {
        if(binding.etSubwayOnboardingName.text.toString() != "") {
            binding.etSubwayOnboardingName.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etSubwayOnboardingName.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)

        }
    }

    private fun nullCheck() {
        binding.etSubwayOnboardingName.setOnClickListener {
            initTextFieldCheck()
        }
    }


    private fun getList() {
        onBoardingViewModel.getSubwayList()
        onBoardingViewModel.subwayList.observe(this) {
            onBoardingViewModel.searchSubwayList.addAll(it)
            addList()
        }
    }

    private fun addList() {
        onBoardingViewModel.listAddAll.observe(this) {
            if (it == true) {
                editTextWatcher()
            }
        }
    }

    private fun editTextWatcher() = with(binding) {
        etSubwayOnboardingName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val input = binding.etSubwayOnboardingName.text.toString()
                searchingSubway(input)
                initTextFieldCheck()
            }
        })
    }

    private fun searchingSubway(text1: String) {
        val tmpList = ArrayList<SubwayListData>()
        val searchSubwayList = onBoardingViewModel.searchSubwayList
        for (i in 0 until searchSubwayList.size) {
            if (searchSubwayList.get(i).STATION_NM.contains(text1)) {
                tmpList.add(searchSubwayList.get(i))
            }
        }
        subwayAdapter = SubwayAdapter()
        binding.rvOnboardingSubway.adapter = subwayAdapter
        //subwayAdapter.findText = text
        subwayAdapter.setCrewList(tmpList)

        //adpater 클릭 리스너너
       subwayAdapter.setItemClickListener(
            object : SubwayAdapter.ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    val subwayName = subwayAdapter.dataList[position].STATION_NM
                    val subwayLine = subwayAdapter.dataList[position].LINE_NUM

                    if(binding.chipSubwayOnboarding.childCount < 2) {
                        binding.chipSubwayOnboarding.addView(Chip(this@SearchSubwayActivity).apply {
                            val string = "$subwayName $subwayLine"
                            text = string
                            isCloseIconVisible = true
                            setOnCloseIconClickListener { binding.chipSubwayOnboarding.removeView(this) }
                        })
                    } else {
                        shortToast("최대 2개까지 추가할 수 있어요!")
                    }

                }
            })

        /*
        chip.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                //Get all checked chips in the group
                val ids: List<Int> = chipGroup.getCheckedChipIds()
                if (ids.size > 5) {
                    chip.setChecked(false) //force to unchecked the chip
                }
            }
        })

         */
    }

}