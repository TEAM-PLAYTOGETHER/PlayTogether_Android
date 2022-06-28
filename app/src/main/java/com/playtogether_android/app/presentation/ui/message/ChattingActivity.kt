package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
import com.playtogether_android.app.presentation.ui.message.viewmodel.SendMessageViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.message.PostSendMessageData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private lateinit var adapter2: ChatAdapter2
    private var roomId = -1
    private lateinit var name: String
    private var audienceId = -1
    private val sendMessageViewModel: SendMessageViewModel by viewModels()
    private val getChatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        getChatAll()
        changeSendImage()
        initAdapter()

        binding.ivSendMessage.setOnClickListener {
            addChat()
            binding.etMessage.text.clear()
        }
        binding.ivInChattingBack.setOnClickListener {
            finish()
        }
        binding.ivInChattingRefresh.setOnClickListener {
            getChatAll()
            shortToast("새로고침 되었습니다")
        }
    }

    private fun changeSendImage() {
        binding.etMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etMessage.text.isNullOrEmpty()) {
                    binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
                    binding.ivSendMessage.setBackgroundColor(
                        ContextCompat.getColor(
                            this@ChattingActivity,
                            R.color.gray_gray03
                        )
                    )
                    binding.ivSendMessage.isClickable = false
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!binding.etMessage.text.isNullOrEmpty()) {
                    binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message_black)
                    binding.ivSendMessage.setBackgroundColor(
                        ContextCompat.getColor(
                            this@ChattingActivity,
                            R.color.main_green
                        )
                    )
                    binding.ivSendMessage.isClickable = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etMessage.text.isNullOrEmpty()) {
                    binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
                    binding.ivSendMessage.setBackgroundColor(
                        ContextCompat.getColor(
                            this@ChattingActivity,
                            R.color.gray_gray03
                        )
                    )
                    binding.ivSendMessage.isClickable = false
                }
            }

        })
    }

    private fun getIntentData() {
        roomId = intent.getIntExtra("roomId", -1)
        Log.d("messageServer", "$roomId")
        name = intent.getStringExtra("name")!!
        audienceId = intent.getIntExtra("audienceId", -1)
        Log.d("getIntent", "${name}")
        binding.tvInChattingName.text = name
    }

    private fun scrollToBottom() {
        Log.d("message", "scrollToBottom 호출됨")
        val size = adapter2.currentList.size - 1
        Log.d("message", "위치 ${size}")
        binding.rvInChattingChatting.scrollToPosition(size)
    }

    private fun getChatAll() {
        if (roomId != -1) {
            getChatViewModel.getChatList(roomId)
            getChatViewModel.chatData.observe(this) {
                adapter2.submitList(it) {
                    scrollToBottom()
                    removeTimeAll()
                }
            }
        }
    }

    private fun observeGetSendMessage() {
        sendMessageViewModel.getSendMessage.observe(this) {
            if (it.success) {
                roomId = it.roomId.toString().toInt()
                getChatAll()
            } else {
                Log.d("messageServer", "아직 메시지 전송 처리 안 끝남")
            }
        }
    }

    private fun addChat() {
        sendMessageViewModel.postSendMessage(
            PostSendMessageData(
                binding.etMessage.text.toString(),
                audienceId
            )
        )
        observeGetSendMessage()
    }


    private fun removeTimeAll() {
        var nowSize = adapter2.currentList.size - 1
        var tempSize = nowSize - 1

        if (tempSize < 0)
            return

        while (true) {
            if (adapter2.currentList[tempSize].timeVisible == false) {
                break
            }
            if (adapter2.currentList[tempSize].messageType == adapter2.currentList[nowSize].messageType) {
                if (adapter2.currentList[nowSize].time == adapter2.currentList[tempSize].time) {
                    adapter2.currentList[tempSize].timeVisible = false
                }
            }
            nowSize = tempSize
            tempSize--
            if (tempSize < 0) break
        }
        adapter2.submitList(adapter2.currentList) {
        }
    }

    /*private fun removeTimePart() {
        Log.d("chatServer", "size : ${adapter2.currentList.size}")
        val nowSize = adapter2.currentList.size - 1
        var tempSize = nowSize - 1

        if (tempSize < 0)
            return

        while (true) {
            if (adapter2.currentList[tempSize].messageType == adapter2.currentList[nowSize].messageType) {
                Log.d("chatServer", "messageType 같음")
                Log.d("chatServer", "tempSize : ${adapter2.currentList[tempSize].time}")
                Log.d("chatServer", "nowSize : ${adapter2.currentList[nowSize].time}")
                if (adapter2.currentList[nowSize].time == adapter2.currentList[tempSize].time) {
                    adapter2.currentList[tempSize].timeVisible = false
                    Log.d("chatServer", "time 같음")
                    tempSize--
                } else
                    break
            } else
                break

            if (tempSize < 0) break
        }
        Log.d("chatServer", "한 번 클릭 끝")
        adapter2.submitList(adapter2.currentList){
            Log.d("messageServer", "removePart 호출됨")
        }
    }*/

    private fun initAdapter() {
        adapter2 = ChatAdapter2()
        binding.rvInChattingChatting.adapter = adapter2
        binding.rvInChattingChatting.addItemDecoration(VerticalItemDecoration())
        scrollToBottom()
    }
}