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
            messageViewModel.getMessageList()
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageViewModel.getMessageList()
        initAdapter()
        initList()
        refreshView()
    }

    private fun initAdapter() {
        adapter = MessageListAdapter {
            clickItem(it)
        }
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

    private fun refreshView() {
        with(binding) {
            lsrlMessageContainer.setOnRefreshListener {
                //해당 부분에 애니메이션 넣는건가? ex) 배경 0.5초 검은색
                initAdapter()
                lsrlMessageContainer.isRefreshing = false
            }
        }
    }
}