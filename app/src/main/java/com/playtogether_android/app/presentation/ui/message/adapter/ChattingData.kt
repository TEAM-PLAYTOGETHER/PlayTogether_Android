package com.playtogether_android.app.presentation.ui.message.adapter

sealed class ChattingData() {
    abstract val viewType: Int

    companion object {
        const val DATE = 0
        const val LOADING = 1
        const val CHAT = 2
    }
}

data class ChatData2(
    override val viewType: Int = CHAT,
    val messageId: Int?,
    val content: String,
    var time: String,
    val messageType: Boolean,
    var timeVisible: Boolean = true,
    var shownTime : String = ""
) : ChattingData() {
    private val chatViewType = if (messageType) MY_CHAT else OTHER_CHAT
    fun getChatViewType(): Int = chatViewType

    companion object {
        const val MY_CHAT = 3
        const val OTHER_CHAT = 4
    }
}

data class DateData(
    override val viewType: Int = DATE,
    private val date: String,
) : ChattingData() {
    fun getDate(): String {
        return date
    }
}

data class LoadingData(
    override val viewType: Int = LOADING
) : ChattingData()