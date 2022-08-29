package com.playtogether_android.data.model.response.search

import com.playtogether_android.domain.model.search.SearchData

data class SearchResult(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SearchData
)
