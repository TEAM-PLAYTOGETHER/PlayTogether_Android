package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.adapter.ChattingAdapter
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChattingViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.message.ChatData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private val chattingViewModel: ChattingViewModel by viewModels()
    private val chattingAdapter: ChattingAdapter by lazy { ChattingAdapter() }
    var roomId: Int = -1
    var recvId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = chattingViewModel
        binding.lifecycleOwner = this

        getIdFromIntent()
        setName()
        initAdapter()

        observeFirstPage()
        observeLoading()
        clickBackArrow()
        clickSendMessage()
    }

    override fun onResume() {
        super.onResume()
        chattingViewModel.getChatList(roomId)
        chattingViewModel.connectSocket(this)
        chattingViewModel.enterRoom(roomId, recvId) { handleSocketError() }
        chattingViewModel.listenSocketConnection(
            { handleSocketError() },
            { runOnUiThread { finish() } })
        chattingViewModel.listenSocketMessage(
            { addChatToRecyclerView(it) },
            { handleSocketError() }
        )
    }

    override fun onPause() {
        super.onPause()
        chattingViewModel.exitRoom()
        chattingViewModel.isFirstPage = true
    }

    private fun getIdFromIntent() {
        intent.run {
            roomId = getIntExtra("roomId", -1)
            recvId = getIntExtra("audienceId", -1)
        }
    }

    private fun setName() {
        val name = intent.getStringExtra("name")
        binding.tvInChattingName.text = name
    }

    private fun clickBackArrow() {
        binding.ivInChattingBack.setOnClickListener { finish() }
    }

    private fun initAdapter() {
        binding.rvInChattingChatting.adapter = chattingAdapter
        binding.rvInChattingChatting.itemAnimator = null
        detectScrollReachTop()
    }

    private fun detectScrollReachTop() {
        binding.rvInChattingChatting.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    if (!binding.rvInChattingChatting.canScrollVertically(-1)) {
                        if (chattingViewModel.isLastPage) return
                        chattingViewModel.isLoading.value = true
                        chattingAdapter.initLoading()
                        binding.rvInChattingChatting.scrollToPosition(chattingAdapter.itemCount - 1)
                        chattingViewModel.getMoreChatList(roomId)
                    }
                }
            }
        })
    }

    private fun observeFirstPage() {
        chattingViewModel.chattingList.observe(this) {
            if (chattingViewModel.isFirstPage) {
                chattingAdapter.initList(it)
            }
        }
    }

    private fun observeLoading() {
        chattingViewModel.isLoading.observe(this) {
            if (chattingViewModel.isLoading.value == false) {
                chattingAdapter.deleteLoading()
                chattingViewModel.addedChattingList.value?.let { list ->
                    chattingAdapter.addList(list, chattingViewModel.lastProfileVisible)
                }
            }
        }
    }

    private fun clickSendMessage() {
        binding.ivSendMessage.setOnClickListener {
            chattingViewModel.sendMessage(binding.etMessage.text.toString(), recvId)
            binding.etMessage.text.clear()
        }
    }

    private fun handleSocketError() {
        runOnUiThread {
            shortToast("서버 통신이 원활하지 않습니다.")
            finish()
        }
    }

    private fun addChatToRecyclerView(chatData: ChatData) {
        runOnUiThread {
            chattingAdapter.addChat(chatData)
            scrollToBottom()
        }
    }

    private fun scrollToBottom() {
        binding.rvInChattingChatting.scrollToPosition(0)
    }
}