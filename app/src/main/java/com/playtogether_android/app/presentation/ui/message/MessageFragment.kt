package com.playtogether_android.app.presentation.ui.message

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentMessageBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.message.viewmodel.MessageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageFragment :
    BaseFragment<FragmentMessageBinding>(R.layout.fragment_message) {
    private lateinit var adapter: MessageListAdapter
    private val messageViewModel: MessageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageViewModel.getMessageList()
        initAdapter()
        messageViewModel.messageData.observe(viewLifecycleOwner) {
            adapter.messageList.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        adapter = MessageListAdapter {
            val intent = Intent(requireContext(), ChattingActivity::class.java)
            intent.putExtra("roomId", it.roomId)
            intent.putExtra("name", it.audience)
            intent.putExtra("audienceId", it.audienceId)
            startActivity(intent)
        }
        binding.rvMessageRoom.adapter = adapter
    }
}