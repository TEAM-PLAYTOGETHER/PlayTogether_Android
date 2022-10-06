package com.playtogether_android.app.presentation.ui.message

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentMessageBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.message.viewmodel.MessageViewModel
import com.playtogether_android.domain.model.message.MessageData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment :
    BaseFragment<FragmentMessageBinding>(R.layout.fragment_message) {
    private lateinit var adapter: MessageListAdapter
    private val messageViewModel: MessageViewModel by viewModels()
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            getMessageRoomList()
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMessageRoomList()
        initAdapter()
        initList()
    }

    override fun onResume() {
        super.onResume()
        getMessageRoomList()
        messageViewModel.initSocket(requireContext())
        messageViewModel.resConnect()
        messageViewModel.resNewMessageToUser()
    }

    override fun onPause() {
        super.onPause()
        messageViewModel.disconnect()
    }

    private fun getMessageRoomList() {
        messageViewModel.getMessageList()
    }

    private fun initAdapter() {
        adapter = MessageListAdapter { clickItem(it) }
        binding.rvMessageRoom.itemAnimator=null
        binding.rvMessageRoom.adapter = adapter
    }

    private fun initList() {
        messageViewModel.messageData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun clickItem(data: MessageData) {
        val intent = Intent(requireContext(), ChattingActivity::class.java)
        intent.putExtra("roomId", data.roomId)
        intent.putExtra("name", data.audience)
        intent.putExtra("audienceId", data.audienceId)
        resultLauncher.launch(intent)
    }
}