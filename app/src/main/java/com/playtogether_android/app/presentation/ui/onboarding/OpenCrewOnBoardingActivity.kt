package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenCrewOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.util.PlayTogetherSharedPreference
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.onboarding.MakeCrewItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.regex.Pattern


@AndroidEntryPoint
class OpenCrewOnBoardingActivity :
    BaseActivity<ActivityOpenCrewOnBoardingBinding>(R.layout.activity_open_crew_on_boarding) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
        nullCheck()
        nameTextWatcher()
        introTextWatcher()
        openCrewNetwork()
        activeBtn()
    }


    //뒤로가기 버튼 리스너
    private fun backBtnListener() {
        binding.ivOpenOnboardingBack.setOnClickListener {
            startActivity(Intent(this, SelectOnboardingActivity::class.java))
            finish()
        }
    }


    //et 비었는지 체크
    private fun nullCheck() {
        binding.etOpenOnboardingName.setOnClickListener { initTextFieldCheck() }
        binding.etOpenOnboardingIntro.setOnClickListener { initTextFieldCheck() }
    }


    private fun activeBtn() {
        if (binding.tvOpenOnboardingApprove.visibility == View.VISIBLE && binding.etOpenOnboardingIntro.text.toString() != "") {
            binding.tvOpenOnboardingNext.isSelected = true
            openCrewNetwork()
        } else { binding.tvOpenOnboardingNext.isSelected = false }
    }


    //backgroundresource 변경
    private fun initTextFieldCheck() {
        if (binding.etOpenOnboardingName.text.toString() != "") {
            binding.etOpenOnboardingName.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etOpenOnboardingName.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

        if (binding.etOpenOnboardingIntro.text.toString() != "") {
            binding.etOpenOnboardingIntro.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etOpenOnboardingIntro.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }
    }


    //이름 textWatcher
    private fun nameTextWatcher() = with(binding) {
        etOpenOnboardingName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val name = p0.toString()
                if (name.length > 15) {
                    binding.tvOpenOnboardingCheck.isSelected = false
                    binding.tvOpenOnboardingWarn.setTextColor(Color.parseColor("#FF0000"))
                } else if (name.isEmpty()) {
                    binding.tvOpenOnboardingCheck.isSelected = false
                    binding.tvOpenOnboardingWarn.setTextColor(Color.parseColor("#C5C5C5"))
                } else {
                    binding.tvOpenOnboardingCheck.isSelected = true
                    binding.tvOpenOnboardingWarn.setTextColor(Color.parseColor("#C5C5C5"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                val name = p0.toString()
                binding.tvOpenOnboardingCheck.setOnClickListener {
                    if (name.length < 16) {
                        onBoardingViewModel.crewName.value = p0.toString()
                        binding.tvOpenOnboardingApprove.visibility = View.VISIBLE
                    }
                    activeBtn()
                }

                val crewName = onBoardingViewModel.crewName.value
                if (crewName != etOpenOnboardingName.text.toString()) { tvOpenOnboardingApprove.visibility = View.INVISIBLE }

                etOpenOnboardingName.isSelected = etOpenOnboardingName.text.toString() != ""
                initTextFieldCheck()
                activeBtn()
            }
        })
    }

    //소개 textWatcher
    private fun introTextWatcher() = with(binding) {
        etOpenOnboardingIntro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etOpenOnboardingIntro.isSelected = etOpenOnboardingIntro.text.toString() != ""
                initTextFieldCheck()
                activeBtn()
            }
        })
    }


    //동아리 개설 서버통신
    private fun openCrewNetwork() {
        binding.tvOpenOnboardingNext.setOnClickListener {
            onBoardingViewModel.requestMakeCrew.crewName =
                binding.etOpenOnboardingName.text.toString()
            onBoardingViewModel.requestMakeCrew.description =
                binding.etOpenOnboardingIntro.text.toString()

            onBoardingViewModel.postMakeCrew(
                MakeCrewItem(
                    onBoardingViewModel.requestMakeCrew.crewName,
                    onBoardingViewModel.requestMakeCrew.description
                )
            )
            observeOpenCrew()
        }
    }


    //동아리 개설 observe
    private fun observeOpenCrew() {
        onBoardingViewModel.makeCrew.observe(this) {
            if (it.success) {
                Timber.d("${it.code}")
                PlayTogetherRepository.crewId = it.id
                Timber.e("Test Repository : ${PlayTogetherRepository.crewId}")
                val intent = Intent(this, OnBoardingIntroduceActivity::class.java).apply{
                    putExtra("crewName", it.name)
                    putExtra("crewCode", it.code)
                    putExtra("crewId", it.id)
                    putExtra("crewIntro", binding.etOpenOnboardingIntro.text.toString())
                    putExtra("isOpener", true)
                }
                Timber.e("${it.name} ${it.code} ${it.id} ${binding.etOpenOnboardingIntro.text.toString()}")

                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "동아리 개설이 실패되었습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
