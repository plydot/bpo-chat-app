package com.chat.bposeats.chat.messages

import com.chat.bposeats.BPChatApp
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONObject

class ChatMessagesPresenter: BasePresenter(), ChatMessagesContract.MPresenter {
    private lateinit var mView: ChatMessagesContract.MView
    private lateinit var dataController: ChatMessagesDataController
    private lateinit var mSocket: Socket

    override fun onViewInitialized() {
        getNewMessages(mView.getViewArguments()?.getStringArray("userIds")?.asList())

    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as ChatMessagesContract.MView
    }

    override fun attachDataController(view: BaseContract.MView) {
        super.attachDataController(view)
        dataController = ChatMessagesDataController(bDataController.dao)
    }

    override fun getNewMessages(users: List<String>?) {
        dataController.bindChatMessages(
            baseView.getLifeCycleOwnerInstance(),
            users!!,
            mView::updateMessageList
        )
    }

    override fun addNewMessage(message: String, user: User) {
        dataController.saveNewChatMessage(
            message,
            user,
            mView::updateWithNewMessage
        )
    }

    override fun sendNewMessage(message: String, phone: String) {
        mSocket = getSocket()
        val obj = JSONObject()
        obj.put("recipient", phone)
        obj.put("message", message)
        mSocket.emit("send_message", obj)
        mSocket.on("message_reply", messageEmitter())
    }

    override fun messageEmitter(): Emitter.Listener {
        return Emitter.Listener { args ->
            val data = args[0].toString().split("__SEP__")
            try {
                addNewMessage(args[0].toString(), dataController.getUserByPhone(data[1])!!)
            }catch (e: Exception){}
        }
    }
}