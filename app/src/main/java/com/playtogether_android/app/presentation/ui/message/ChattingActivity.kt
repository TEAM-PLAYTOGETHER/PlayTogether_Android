package com.playtogether_android.app.presentation.ui.message

import android.content.Intent
import android.os.Bundle
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
        initAdapter()
        removeTimeAll()
        binding.ivSendMessage.setOnClickListener{
            addChat()
            binding.etMessage.text.clear()
            removeTimePart()
        }
    }

    private fun changeSendImage(){
        if(binding.etMessage.text.isNullOrEmpty()){
            binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
            binding.ivSendMessage.setBackgroundColor(ContextCompat.getColor(this ,R.color.gray_gray03))
        }
        else{
            binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message_black)
            binding.ivSendMessage.setBackgroundColor(ContextCompat.getColor(this ,R.color.main_green))
        }
    }

    private fun getIntentData(){
        val intent = Intent()
        val roomId = intent.getIntExtra("roomId", -1)
        val name = intent.getStringExtra("name")
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