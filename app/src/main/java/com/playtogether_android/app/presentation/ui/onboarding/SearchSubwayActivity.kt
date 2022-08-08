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

    private lateinit var recipeList: ArrayList<SubwayListData>
    private lateinit var ingredientsList: ArrayList<SubwayData>

    private lateinit var recipeAdapter: SearchAfterAdapter
    private lateinit var resultAdapter: SearchResultAdapter
    private lateinit var ingredientAdapter: SearchInSubwayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayNetwork()
        getList()
        observingWord()
        observingResult()
        searchingRecipes("아")
    }

    private fun observingResult() {
        onBoardingViewModel.subwaySearchList.observe(this) {
            resultAdapter.submitList(it as MutableList<SubwayListData>)
        }
    }


    private fun subwayNetwork() {

        onBoardingViewModel.getSubwayList()
        //onboardingSubwayAdapter = OnboardingSubwayAdapter()
        onBoardingViewModel.subwayList.observe(this) {
            //Timber.d("SubwayList : $it")
            //onboardingSubwayAdapter.setSubwayList((it) as MutableList<SubwayListData>)
        }


    }

    private fun observingWord() {
        /*
        searchingRecipes("아")
        searchingIngredients("아")
        onBoardingViewModel.searchingWord.observe(this) {
            Timber.d("TEST WORD: $it")
            searchingRecipes("아")
            searchingIngredients("아")
        }

         */
    }

    private fun searchingRecipes(text: String) {
        val tmpList = ArrayList<SubwayListData>()
        Timber.d("kkkkkkkk : $recipeList")
        for (i in 0..recipeList.size - 1) {
            if (recipeList.get(i).STATION_NM.contains(text)) {
                tmpList.add(recipeList.get(i))
                Timber.d("TEST TMP LIST : $tmpList")
            }
        }
//        recipeAdapter.findText = text
        Timber.d("TEST TMP LIST2 : $tmpList")
        recipeAdapter.submitList(tmpList)
    }

    private fun searchingIngredients(text: String) {
        val tmpList = ArrayList<SubwayData>()
        Timber.d("TESTTEST : $ingredientsList")
        for (i in 0..ingredientsList.size - 1) {
            if (ingredientsList.get(i).STATION_NM.contains(text)) {
                tmpList.add(ingredientsList.get(i))
                Timber.d("ingredientsListList : $tmpList")
            }
        }
        ingredientAdapter.findText = text
        ingredientAdapter.submitList(tmpList)
    }

    private fun getList() {
        recipeList = ArrayList<SubwayListData>()
        onBoardingViewModel.getSubwayList()
        onBoardingViewModel.subwayList.observe(this) {
            recipeList.addAll(it)
            Timber.d("AddList : $it")
        }

        ingredientsList = ArrayList()
        onBoardingViewModel.subwaySearchList.observe(this) {
            ingredientsList.addAll(it)
            Timber.d("searchList : $it")
        }
    }
}