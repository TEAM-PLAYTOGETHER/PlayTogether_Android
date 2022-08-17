package com.playtogether_android.app.presentation.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnboardingReDownLoadBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.OnboardingReDownAdapter
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.domain.model.onboarding.CrewListData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber


@AndroidEntryPoint
class OnboardingReDownLoadActivity : BaseActivity<ActivityOnboardingReDownLoadBinding>(R.layout.activity_onboarding_re_down_load) {


    private lateinit var onboardingReDownAdapter: OnboardingReDownAdapter
    private val onBoardingViewModel : OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // initTouchListener()

    }

    override fun onResume() {
        super.onResume()
        initList()
    }

    private fun initList() {
        Timber.d("TEST CODE1")
        onBoardingViewModel.getCrewList()

//        onBoardingViewModel.getCrewList.observe(this) {
//            Timber.d("TEST CODE")
//            onBoardingViewModel.getCrewList()
//        }

        onboardingReDownAdapter = OnboardingReDownAdapter()
        binding.rvLikeLinear.adapter = onboardingReDownAdapter
        onBoardingViewModel.getCrewList.observe(this) {
            onboardingReDownAdapter.setCrewList((it.data.crewList) as MutableList<CrewListData.Data.CrewList>)
        }

        onboardingReDownAdapter.setItemClickListener(
            object : OnboardingReDownAdapter.ItemClickListener{
                override fun onClick(view: View, position: Int) {
                    val crewId = onboardingReDownAdapter.dataList[position].id
                    Timber.d("CrewId : $crewId")
                }

            }
        )


    }

    private fun initTouchListener() {
        binding.rvLikeLinear.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented")
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                if(e.action == MotionEvent.ACTION_MOVE){

                }
                else{

                    val child = rv.findChildViewUnder(e.x, e.y)
                    if(child != null){
                        val position = rv.getChildAdapterPosition(child)
                        val view = rv.layoutManager?.findViewByPosition(position)
                        view?.isSelected = true

                        for(i in 0..rv.adapter!!.itemCount){
                            val otherView = rv.layoutManager?.findViewByPosition(i)
                            if(otherView != view){
                                otherView?.isSelected = false

                            }
                        }
                    }
                }

                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}