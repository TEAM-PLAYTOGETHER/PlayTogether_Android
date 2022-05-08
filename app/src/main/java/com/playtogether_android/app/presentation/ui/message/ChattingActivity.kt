package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.domain.model.message.ChatData

class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private lateinit var adapter:ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        changeSendImage()
        initAdapter()
        removeTimeAll()

        binding.ivSendMessage.setOnClickListener{
            binding.ivSendMessage.isClickable = !binding.etMessage.text.isNullOrEmpty()
            addChat()
            binding.etMessage.text.clear()
            removeTimePart()
        }
        binding.ivInChattingBack.setOnClickListener{
            finish()
        }
    }

    private fun changeSendImage() {
        binding.etMessage.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.etMessage.text.isNullOrEmpty()){
                    binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
                    binding.ivSendMessage.setBackgroundColor(ContextCompat.getColor(this@ChattingActivity, R.color.gray_gray03))
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!binding.etMessage.text.isNullOrEmpty()){
                    binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message_black)
                    binding.ivSendMessage.setBackgroundColor(ContextCompat.getColor(this@ChattingActivity, R.color.main_green))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.etMessage.text.isNullOrEmpty()){
                    binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
                    binding.ivSendMessage.setBackgroundColor(ContextCompat.getColor(this@ChattingActivity, R.color.gray_gray03))
                }
            }

        })
    }

    private fun getIntentData(){
        val roomId = intent.getIntExtra("roomId", -1)
        val name = intent.getStringExtra("name")
        Log.d("getIntent", "${name}")
        binding.tvInChattingName.text=name
    }

    private fun scrollToBottom(){
        val size = adapter.chatList.size-1
        binding.rvInChattingChatting.scrollToPosition(size)
    }

    private fun addChat(){
        adapter.chatList.add(ChatData(binding.etMessage.text.toString(), "22.05.06 오후 06:27", 0))
        adapter.notifyDataSetChanged()
        scrollToBottom()
    }

    private fun removeTimeAll(){
        var nowSize = adapter.chatList.size-1
        var tempSize = nowSize-1

        if(tempSize<0)
            return

        while(true){
            if(adapter.chatList[tempSize].messageType==adapter.chatList[nowSize].messageType){
                if(adapter.chatList[nowSize].time==adapter.chatList[tempSize].time){
                    adapter.chatList[tempSize].timeVisible=false
                }
            }
            else
                nowSize=tempSize

            tempSize--
            if(tempSize<0) break
        }
        adapter.notifyDataSetChanged()
    }
    private fun removeTimePart(){
        val nowSize = adapter.chatList.size-1
        var tempSize = nowSize-1

        if(tempSize<0)
            return

        while(true){
            if(adapter.chatList[tempSize].messageType==adapter.chatList[nowSize].messageType){
                if(adapter.chatList[nowSize].time==adapter.chatList[tempSize].time){
                    adapter.chatList[tempSize].timeVisible=false
                    tempSize--
                }
                else
                    break
            }
            else
                break

            if(tempSize<0) break
        }
        adapter.notifyDataSetChanged()
    }
    private fun initAdapter(){
        adapter= ChatAdapter()
        binding.rvInChattingChatting.adapter=adapter
        binding.rvInChattingChatting.addItemDecoration(VerticalItemDecoration())
        adapter.chatList.addAll(
            listOf(
                ChatData("안녕하세요", "22.04.23 오후 06:27", 0),
                ChatData("안녕하세요","22.04.23 오후 06:27", 1),
                ChatData("네 식사는 하셨나요?", "22.04.23 오후 06:27",0),
                ChatData("네 점심 먹었어요", "22.04.23 오후 06:28",1),
                ChatData("용민님도 점심 드셨나요?", "22.04.23 오후 06:28",1),
                ChatData("아니요 굶었습니다", "22.04.23 오후 06:28",0),
                ChatData("네 좋습니다!", "22.04.23 오후 06:28",1)
            )
        )
        scrollToBottom()
    }
}