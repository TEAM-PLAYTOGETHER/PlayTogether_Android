package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
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
        binding.etOpenOnboardingName.setOnClickListener {
            initTextFieldCheck()
        }

        binding.etOpenOnboardingIntro.setOnClickListener {
            initTextFieldCheck()
        }
    }

    private fun activeBtn() {
        if (binding.etOpenOnboardingName.text.toString() == "" || binding.etOpenOnboardingIntro.text.toString() == "") {
            binding.tvOpenOnboardingNext.isSelected = false
        } else {
            binding.tvOpenOnboardingNext.isSelected = true
            openCrewNetwork()
        }
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
                if (!Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z|0-9|]{1,15}\$", name)) {
                    binding.tvOpenOnboardingApprove.visibility = View.INVISIBLE
                    binding.tvOpenOnboardingWarn.visibility = View.VISIBLE
                } else {
                    binding.tvOpenOnboardingApprove.visibility = View.VISIBLE
                    binding.tvOpenOnboardingWarn.visibility = View.INVISIBLE

                }

            }

            override fun afterTextChanged(p0: Editable?) {
                block()
                etOpenOnboardingName.isSelected = etOpenOnboardingName.text.toString() != ""
                initTextFieldCheck()

                activeBtn()

            }
        })
    }

    private fun block() {
        binding.etOpenOnboardingName.setFilters(arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isLetterOrDigit(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }))
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
                val intent = Intent(this, OnBoardingIntroduceActivity::class.java)
                intent.putExtra("crewName" , it.name)
                intent.putExtra("crewCode", it.code)
                intent.putExtra("crewIntro", binding.etOpenOnboardingIntro.text.toString())
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "동아리 개설이 실패되었습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
