package com.playtogether_android.app.presentation.ui.message

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentMessageBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.message.viewmodel.MessageViewModel
import com.playtogether_android.domain.model.message.MessageData
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageFragment :
    BaseFragment<FragmentMessageBinding>(R.layout.fragment_message){
    private lateinit var adapter : MessageListAdapter
    private val messageViewModel : MessageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageViewModel.getMessageList()
        initAdapter()
        messageViewModel.messageData.observe(viewLifecycleOwner){
            adapter.messageList.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter(){
        adapter= MessageListAdapter {
            val intent = Intent(requireContext(), ChattingActivity::class.java)
            intent.putExtra("roomId", it.roomId)
            intent.putExtra("name", it.audience)
            intent.putExtra("audienceId", it.audienceId)
            startActivity(intent)
        }
        binding.rvMessageRoom.adapter=adapter
        /*adapter.messageList.addAll(
            listOf(
                MessageData(0,"이혜빈", false, "22.05.06 오후 05:06", "야 공부하라했지 뒤질래?"),
                MessageData(0,"김세훈", true, "22.05.06 오후 03:55", "아 형 언제와;;"),
                MessageData(0,"최정원", true, "22.05.06 오전 02:32", "용미나 정처기 못하겠어,,"),
                MessageData(0,"이종찬", true, "22.05.05 오후 12:17", "어렵군"),
            )
        )*/
    }
}