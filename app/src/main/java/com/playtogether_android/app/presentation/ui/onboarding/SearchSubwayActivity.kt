package com.playtogether_android.app.presentation.ui.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySearchSubwayBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.SubwayAdapter
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.onboarding.SubwayListData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchSubwayActivity :
    BaseActivity<ActivitySearchSubwayBinding>(R.layout.activity_search_subway) {

    private val chipList = java.util.ArrayList<String>()
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private lateinit var subwayAdapter: SubwayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getList()
        nullCheck()
        addBtnClickListener()
        initSetting()
        activeBtn()
    }


    private fun initTextFieldCheck() {
        if (binding.etSubwayOnboardingName.text.toString() != "") {
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
        subwayAdapter.findText = text1
        subwayAdapter.setCrewList(tmpList)

        //adpater 클릭 리스너
        subwayAdapter.setItemClickListener(
            object : SubwayAdapter.ItemClickListener {
                @SuppressLint("ResourceAsColor")
                override fun onClick(view: View, position: Int) {
                    val subwayName = subwayAdapter.dataList[position].STATION_NM
                    var subwayLine = subwayAdapter.dataList[position].LINE_NUM

                    if(subwayLine.contains("0")) {
                        subwayLine = subwayLine.replace("0","")
                    }
                    else if(subwayLine == "경의선") {
                        subwayLine = subwayLine.replace("경의선","경의중앙선")
                    }
                    else if(subwayLine == "인천선") {
                        subwayLine = subwayLine.replace("인천선","인천1호선")
                    }
                    else if(subwayLine == "우이신설경전철") {
                        subwayLine = subwayLine.replace("우이신설경전철","우이신설선")
                    }
                    else if(subwayLine == "북한산우이") {
                        subwayLine = subwayLine.replace("북한산우이","우이신설선")
                    }
                    else if(subwayLine == "김포공항") {
                        subwayLine = subwayLine.replace("김포공항","김포골드라인")
                    }
                    else if(subwayLine=="김포도시철도"){
                        subwayLine = subwayLine.replace("김포도시철도","김포골드라인")
                    }
                    else if(subwayLine=="용인경전철"){
                        subwayLine = subwayLine.replace("용인경전철","에버라인")
                    }


                    //칩버튼 커스텀
                    if (binding.chipSubwayOnboarding.childCount < 2) {
                        binding.chipSubwayOnboarding.addView(Chip(this@SearchSubwayActivity).apply {
                            val string = "$subwayName $subwayLine"
                            text = string
                            setTextColor(getColorStateList(R.color.main_green))
                            isCloseIconVisible = true
                            setCloseIconResource(R.drawable.icn_exit)
                            setCloseIconTintResource(R.color.gray_999999)
                            chipBackgroundColor = getColorStateList(R.color.black)
                            setOnCloseIconClickListener {
                                binding.chipSubwayOnboarding.removeView(
                                    this
                                )
                                activeBtn()
                            }
                        })
                    } else {
                        shortToast("최대 2개까지 추가할 수 있어요!")
                    }
                    activeBtn()
                }
            })
    }


    private fun addListener() {
        for (i: Int in 1..binding.chipSubwayOnboarding.childCount) {
            val chip: Chip = binding.chipSubwayOnboarding.getChildAt(i - 1) as Chip
            chipList.add(chip.text.toString())
        }
    }


    private fun initSetting() {
        val list = intent.getStringArrayListExtra("ChipList")
        val nickname = intent.getStringExtra("nickname")
        val description = intent.getStringExtra("description")
        val nicknameCheck = intent.getBooleanExtra("nicknameCheck", false)

        if (list?.size != null) {
            for (i in 0 until list.size) {
                val chip = Chip(binding.chipSubwayOnboarding.context).apply {
                    text = list[i]
                    setTextColor(getColorStateList(R.color.main_green))

                    isCloseIconVisible = true
                    setCloseIconResource(R.drawable.icn_exit)
                    setCloseIconTintResource(R.color.gray_999999)
                    chipBackgroundColor = getColorStateList(R.color.black)
                    setOnCloseIconClickListener {
                        binding.chipSubwayOnboarding.removeView(
                            this
                        )
                    }
                }
                binding.chipSubwayOnboarding.addView(chip)
            }
        }
        binding.ivSubwayOnboardingBack.setOnClickListener {
            val intent = Intent(this, OnBoardingIntroduceActivity::class.java)
            if (list != null) {
                if(list.size != 0 ) {
                    intent.putExtra("ChipList", list)
                }
                intent.putExtra("nickname", nickname)
                intent.putExtra("description", description)
                intent.putExtra("nicknameCheck", nicknameCheck)
            }

            startActivity(intent)
            finish()
        }
    }

    private fun addBtnClickListener() {
        binding.tvIntroOnboardingNext.setOnClickListener {
            addListener()
            val nickname = intent.getStringExtra("nickname")
            val description = intent.getStringExtra("description")
            val nicknameCheck = intent.getBooleanExtra("nicknameCheck", false)
            val intent = Intent(this, OnBoardingIntroduceActivity::class.java)
            intent.putExtra("ChipList", chipList)
            intent.putExtra("nickname", nickname)
            intent.putExtra("description", description)
            intent.putExtra("nicknameCheck", nicknameCheck)
            startActivity(intent)
            finish()
        }
    }

    private fun activeBtn() {
        binding.tvIntroOnboardingNext.isSelected = binding.chipSubwayOnboarding.childCount > 0
    }

}