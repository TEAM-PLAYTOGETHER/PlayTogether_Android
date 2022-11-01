package com.playtogether_android.app.presentation.ui.message.socketData

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.playtogether_android.app.presentation.ui.message.socketData.request.ReqEnterRoom
import com.playtogether_android.app.presentation.ui.message.socketData.request.ReqSendMessage
import com.playtogether_android.app.presentation.ui.message.socketData.response.*
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.message.ChatData
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import timber.log.Timber
import java.net.URISyntaxException

class ChattingSocket {
    private lateinit var socket: Socket
    private val gson by lazy { Gson() }

    fun initSocket(context: Context) {
        try {
            onConnect()
        } catch (e: URISyntaxException) {
            Toast.makeText(context, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT)
            Timber.e("Socket Connection Fail")
        }
        socket.connect()
    }

    private fun onConnect() {
        val token = PlayTogetherRepository.userToken
        val tokenLst = listOf(token)
        val header: MutableMap<String, List<String>> = HashMap()
        header["Authorization"] = tokenLst
        val options = IO.Options()
        options.extraHeaders = header
        socket = IO.socket("http://13.125.232.150:3000", options)
        Timber.e("Socket connection success : ${socket.id()}")
    }

    fun resConnect(lambda: () -> Unit) {
        val connection = Emitter.Listener {
            val temp = gson.fromJson(it[0].toString(), ResConnection::class.java)
            if (!temp.success) lambda()
            Timber.e("Socket ResConnection : $temp")
        }
        socket.on("resConnection", connection)
    }

    fun reqEnterRoom(roomId: Int, recvId: Int) {
        val data = ReqEnterRoom(roomId, recvId)
        val jsonData = gson.toJson(data)
        Timber.e("Socket ReqEnterRoom")
        socket.emit("reqEnterRoom", jsonData)
    }

    fun resEnterRoom(lambda: () -> Unit) {
        val enterListener = Emitter.Listener {
            val success: Boolean = gson.fromJson(it[0].toString(), ResEnterRoom::class.java).success
            if (!success) lambda()
            Timber.e("Socket ResEnterRoom : $success")
        }
        socket.on("resEnterRoom", enterListener)
    }

    fun reqSendMessage(text: String, recvId: Int) {
        val message = ReqSendMessage(recvId, text)
        Timber.e("Socket ReqSendMessage content : $message")
        val jsonData = gson.toJson(message)
        socket.emit("reqSendMessage", jsonData)
    }

    fun resSendMessage(
        errorLambda: () -> Unit,
        addChatLambda: (ChatData) -> Unit,
        reformatSocketChat: (String, String, Boolean) -> ChatData
    ) {
        val sendListener = Emitter.Listener {
            val sendData = gson.fromJson(it[0].toString(), ResSendMessage::class.java)
            val sendChat = sendData.data.message
            if (sendData.success) {
                val tempChat = reformatSocketChat(sendChat.content, sendChat.createdAt, true)
                addChatLambda(tempChat)
            } else
                errorLambda()
        }
        socket.on("resSendMessage", sendListener)
    }

    /*fun resNewMessageToUser() {
        val messageListener = Emitter.Listener {
            val receivedChat: NewMessageReceived.Data.Message =
                gson.fromJson(it[0].toString(), NewMessageReceived::class.java).data.message.apply {
                    createdAt = changeDateFormat(createdAt)
                }
            updateRoomList(receivedChat)
        }
        socket.on("newMessageToUser", messageListener)
    }*/

    fun resNewMessageToRoom(
        addChatLambda: (ChatData) -> Unit,
        reformatSocketChat: (String, String, Boolean) -> ChatData
    ) {
        val messageListener = Emitter.Listener {
            val receivedChat: NewMessageReceived.Data.Message =
                gson.fromJson(it[0].toString(), NewMessageReceived::class.java).data.message
            Timber.e("Socket receive chat : $receivedChat")
            val tempChat = reformatSocketChat(receivedChat.content, receivedChat.createdAt, false)
            addChatLambda(tempChat)
        }
        socket.on("newMessageToRoom", messageListener)
    }

    fun reqExitRoom() {
        socket.emit("reqExitRoom")
        Timber.e("Socket ReqExitRoom")
    }

    fun resExitRoom(lamda: () -> Unit) {
        val exitListener = Emitter.Listener {
            val success = gson.fromJson(it[0].toString(), ResExitRoom::class.java).success
            if (!success) lamda()
            else disconnect()
            Timber.e("Socket ResExitRoom : $success")
        }
        socket.on("resExitRoom", exitListener)
    }

    private fun disconnect() {
        socket.disconnect()
        Timber.e("Socket disconnected")
    }
}
