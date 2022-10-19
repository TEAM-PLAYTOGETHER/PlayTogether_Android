/*
package com.playtogether_android.app.presentation.ui.message.socketData

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.playtogether_android.app.presentation.ui.message.data.response.*
import com.playtogether_android.data.singleton.PlayTogetherRepository
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import timber.log.Timber
import java.net.URISyntaxException

object SocketSingleton {
    private lateinit var socket: Socket
    private val gson by lazy { Gson() }

    fun initSocket(context: Context) {
        try {
            onConnect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Toast.makeText(context, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT)
            Timber.e("Socket Connection Fail")
        }
        socket.connect()
    }

    private fun onConnect() {
        val token = PlayTogetherRepository.userToken
        val tokenLst = listOf(token)
        //val httpClient = OkHttpClient.Builder().addNetworkInterceptor(AuthInterceptor()).build()
        val header: MutableMap<String, List<String>> = HashMap()
        header["Authorization"] = tokenLst
        val options = IO.Options()
        options.extraHeaders = header
        */
/*options.webSocketFactory = httpClient
        options.callFactory = httpClient*//*

        socket = IO.socket("http://13.125.232.150:3000", options)
        Timber.e("Socket connection success : ${socket.id()}")
    }

    fun resConnect(){
        val connection = Emitter.Listener {
            val temp = gson.fromJson(it[0].toString(), ResConnection::class.java)
            Timber.e("Socket ResConnection : $temp")
        }
        socket.on("ResConnection", connection)
    }

    fun reqEnterRoom(roomId: Int, recvId: Int) {
        val data = reqEnterRoom(roomId, recvId)
        val jsonData = gson.toJson(data)
        socket.emit("ReqEnterRoom", jsonData)
    }

    fun resEnterRoom(lamda : () -> Unit) {
        val enterListener = Emitter.Listener {
            Timber.e("Socket enter on : $it")
            val success : Boolean = gson.fromJson(it[0].toString(), ResEnterRoom::class.java).success
            if (success)
                lamda()
        }
        socket.on("ResEnterRoom", enterListener)
    }

    fun reqSendMessage(text: String) {
        val message = reqSendMessage(text)
        val jsonData = gson.toJson(message)
        socket.emit("ReqSendMessage", jsonData)
    }

    fun resSendMessage(lamda: () -> Unit){
        val sendListener = Emitter.Listener{
            val success : Boolean = gson.fromJson(it[0].toString(), ResSendMessage::class.java).success
            Timber.e("Socket send on : ${it[0].toString()}")
            if(success)
                lamda()
        }
    socket.on("ResSendMessage", sendListener)
    }

    fun resNewMessageToUser(lamda: (NewMessageReceived.Data.Message) -> Unit){
        val messageListener = Emitter.Listener {
            Timber.e("Socket receive chat : ${gson.fromJson(it[0].toString(), NewMessageReceived::class.java)}")
            val receivedChat : NewMessageReceived.Data.Message
                = gson.fromJson(it[0].toString(), NewMessageReceived::class.java).data.message
            lamda(receivedChat)
        }
        socket.on("NewMessageReceived", messageListener)
    }

    fun resNewMessageToRoom(lamda: (NewMessageReceived.Data.Message) -> Unit){
        val messageListener = Emitter.Listener {
            val receivedChat : NewMessageReceived.Data.Message
                = gson.fromJson(it[0].toString(), NewMessageReceived::class.java).data.message
            lamda(receivedChat)
        }
        socket.on("newMessageToRoom", messageListener)
    }

    fun reqExitRoom(){
        socket.emit("reqExitRoom")
    }

    fun resExitRoom(lamda: () -> Unit){
        val exitListener = Emitter.Listener {
        val success = gson.fromJson(it[0].toString(), ResExitRoom::class.java).success
            if(success)
                lamda
        }
        socket.on("resExitRoom", exitListener)
    }

    fun disconnect(){
        socket.disconnect()
    }
}*/
