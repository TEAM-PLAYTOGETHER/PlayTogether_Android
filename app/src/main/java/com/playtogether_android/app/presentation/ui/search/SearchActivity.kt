package com.playtogether_android.app.presentation.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySearchBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.DO
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.EAT
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.GO
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val adapter: SearchListAdapter by lazy { SearchListAdapter() }
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchingWord : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initSearchList()
        clickSearchingImage()
        clickKeyboardEnter()
        clickFilter()
        observeFiltering()
    }

    private fun initAdapter() {
        detectWhenScrollEnd()
        binding.rvSearch.adapter = adapter
    }

    private fun detectWhenScrollEnd(){
        binding.rvSearch.addOnScrollListener(
            object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(!binding.rvSearch.canScrollVertically(1)){
                        searchViewModel.getSearchList(searchingWord)
                    }
                }
            }
        )
    }

    private fun initSearchList() {
        searchViewModel.searchList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun clickFilter() {
        clickEat()
        clickDo()
        clickGo()
    }

    private fun clickEat() {
        binding.tvSearchEat.setOnClickListener {
            binding.tvSearchEat.isSelected = clickSort(EAT)
        }
    }

    private fun clickDo() {
        binding.tvSearchDo.setOnClickListener {
            binding.tvSearchDo.isSelected = clickSort(DO)
        }
    }

    private fun clickGo() {
        binding.tvSearchGo.setOnClickListener {
            binding.tvSearchGo.isSelected = clickSort(GO)
        }
    }

    private fun clickSort(category: String?): Boolean {
        when(category){
            searchViewModel._category.value -> {
                searchViewModel._category.value = null
                return false
            }
            null -> {
                searchViewModel._category.value = category
                return true
            }
            else -> {
                searchViewModel._category.value = category
                binding.tvSearchEat.isSelected=false
                binding.tvSearchDo.isSelected=false
                binding.tvSearchGo.isSelected=false
                return true
            }
        }
    }

    private fun observeFiltering() {
        searchViewModel._category.observe(this) {
            searchViewModel.getSearchList(binding.etSearch.text.toString())
        }
    }

    private fun clickSearchingImage() {
        binding.ivSearch.setOnClickListener { searching() }
    }

    private fun clickKeyboardEnter() {
        binding.etSearch.setOnEditorActionListener { textView, action, event ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                searching()
                handled = true
            }
            handled
        }
    }

    private fun searching() {
        searchingWord = binding.etSearch.text.toString()
        if (!checkSearchingWordIsValid(searchingWord)) return
        searchViewModel.getSearchList(searchingWord)
    }

    private fun checkSearchingWordIsValid(searchingWord: String): Boolean {
        if (searchingWord.length < 2) {
            shortToast("두 글자 이상 입력해주세요")
            return false
        }
        return true
    }
}