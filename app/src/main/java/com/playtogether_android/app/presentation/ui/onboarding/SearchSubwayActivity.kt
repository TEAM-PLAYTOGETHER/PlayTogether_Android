package com.playtogether_android.app.presentation.ui.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySearchSubwayBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.*
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.domain.model.onboarding.SubwayData
import com.playtogether_android.domain.model.onboarding.SubwayListData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchSubwayActivity :
    BaseActivity<ActivitySearchSubwayBinding>(R.layout.activity_search_subway) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    // private lateinit var onboardingSubwayAdapter: OnboardingSubwayAdapter

//    private var recipeList = onBoardingViewModel.recipeList

    private lateinit var recipeAdapter: SearchAfterAdapter
    private lateinit var resultAdapter: SearchResultAdapter
    private lateinit var ingredientAdapter: SearchInSubwayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayNetwork()
        getList()

    }


    private fun subwayNetwork() {

        onBoardingViewModel.getSubwayList()
        //onboardingSubwayAdapter = OnboardingSubwayAdapter()
        onBoardingViewModel.subwayList.observe(this) {
            Timber.d("SubwayList : $it")
            //onboardingSubwayAdapter.setSubwayList((it) as MutableList<SubwayListData>)
        }
    }


    private fun getList() {
        onBoardingViewModel.getSubwayList()
        onBoardingViewModel.subwayList.observe(this) {
            onBoardingViewModel.searchSubwayList.addAll(it)
            Timber.d("AddList : ${onBoardingViewModel.searchSubwayList}")
            addList()
        }
    }

    private fun addList() {
        onBoardingViewModel.listAddAll.observe(this) {
            if (it == true) {
                observingWord()
            }
        }
    }


    private fun observingWord() {
        searchingRecipes("화")
        onBoardingViewModel.searchingWord.observe(this) {
            Timber.d("TEST WORD: $it")

            //           searchingIngredients(it)
        }


    }

    private fun searchingRecipes(text: String) {
        val tmpList = ArrayList<SubwayListData>()
        val searchSubwayList = onBoardingViewModel.searchSubwayList
        Timber.d("kkkkkkkk : $searchSubwayList")
        for (i in 0..searchSubwayList.size - 1) {
            if (searchSubwayList.get(i).STATION_NM.contains(text)) {
                tmpList.add(searchSubwayList.get(i))
            }
        }
//        recipeAdapter.findText = text
        recipeAdapter = SearchAfterAdapter()
        binding.rvOnboardingSubway.adapter = recipeAdapter
        recipeAdapter.setCrewList(tmpList)
    }


}