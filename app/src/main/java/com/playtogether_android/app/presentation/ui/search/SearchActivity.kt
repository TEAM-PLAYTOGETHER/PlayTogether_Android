package com.playtogether_android.app.presentation.ui.search

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySearchBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.DO
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.EAT
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.FIRST
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.GO
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.MORE
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val adapter: SearchListAdapter by lazy { SearchListAdapter { clickThunderItem(it) } }
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchingWord: String
    private var becauseOfIntent = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel=searchViewModel
        binding.lifecycleOwner=this
        checkIntent()
        initAdapter()
        initSearchList()
        clickSearchingImage()
        clickKeyboardEnter()
        clickFilter()
        observeFiltering()
        clickBackArrow()
    }

    private fun initAdapter() {
        detectWhenScrollEnd()
        binding.rvSearch.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 40
            }
        })
        binding.rvSearch.adapter = adapter
    }

    private fun checkIntent() {
        val category: String? = intent.getStringExtra("category")
        when (category) {
            EAT -> {
                searchViewModel.category.value = category
                binding.tvSearchEat.isSelected = true
            }
            DO -> {
                searchViewModel.category.value = category
                binding.tvSearchDo.isSelected = true
            }
            GO -> {
                searchViewModel.category.value = category
                binding.tvSearchGo.isSelected = true
            }
            else -> searchViewModel.category.value = null
        }
        becauseOfIntent = true
    }

    private fun showToastNoResult() {
        shortToast("검색 결과가 없습니다.")
    }

    private fun detectWhenScrollEnd() {
        binding.rvSearch.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!binding.rvSearch.canScrollVertically(1)) {
                        if (searchViewModel.isLastPage) return
                        searchViewModel.getSearchList(searchingWord, MORE, { showToastNoResult() })
                        Timber.d("Log for recyclerview bottom end")
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
        when (category) {
            searchViewModel.category.value -> {
                searchViewModel.category.value = null
                return false
            }
            null -> {
                searchViewModel.category.value = category
                return true
            }
            else -> {
                searchViewModel.category.value = category
                binding.tvSearchEat.isSelected = false
                binding.tvSearchDo.isSelected = false
                binding.tvSearchGo.isSelected = false
                return true
            }
        }
    }

    private fun observeFiltering() {
        searchViewModel.category.observe(this) {
            val searchingWord = binding.etSearch.text.toString()
            if (becauseOfIntent) {
                becauseOfIntent = false
            } else if (checkSearchingWordIsValid(searchingWord)) {
                searchViewModel.getSearchList(searchingWord, FIRST, { showToastNoResult() })
                searchViewModel.isLastPage = false
            }
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
        searchViewModel.getSearchList(searchingWord, FIRST, { showToastNoResult() })
        searchViewModel.isLastPage = false
    }

    private fun checkSearchingWordIsValid(searchingWord: String): Boolean {
        if (searchingWord.length < 2) {
            shortToast("두 글자 이상 입력해주세요")
            return false
        }
        return true
    }

    private fun clickBackArrow() {
        binding.ivSearchBack.setOnClickListener { finish() }
    }

    private fun clickThunderItem(thunderId: Int) {
        val intent = Intent(this, ThunderDetailActivity::class.java)
        intent.putExtra("thunderId", thunderId)
        startActivity(intent)
    }
}