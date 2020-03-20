package com.chat.bposeats.chat

import android.os.AsyncTask
import android.widget.Toast
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.chat.bposeats.data.network.Routines
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import org.json.JSONObject
import java.util.*

class ChatPresenter : BasePresenter(), ChatContract.MPresenter {

    private lateinit var mView: ChatContract.MView
    private lateinit var dataController: ChatDataController
    private lateinit var mSocket: Socket
    private lateinit var networkService: Routines

    override fun onViewInitialized() {
        dataController.bindActiveUser(
            baseView.getLifeCycleOwnerInstance(),
            mView::getActiveUser
        )
    }

    override fun logInUser(user: List<User>?) {
        if (user == null || user.isEmpty()) {
            mView.displayLoginUi()
        } else {
            mView.displayChatUi()
        }
    }



    override fun getDialogs() {
        dataController.bindChatDialogs(
            baseView.getLifeCycleOwnerInstance(),
            mView::updateDialog
        )
    }

    override fun loadDialogUsers(dialog: IDialog<IMessage>, out: (List<String>) -> (Unit)) {
        val userIds = mutableListOf<String>()
        for (user in dialog.users) {
            userIds.add(user.id)
        }
        userIds.add(getActiveUser()!!.dbId)
        out.invoke(userIds)
    }



    override fun connectSocket(connect: (String) -> Unit) {
        val onConnect = Emitter.Listener { args ->
            try {
                val data = args[0].toString()
                connect.invoke(data)
            }catch (e: NullPointerException){

            }
        }
        mSocket = getSocket()
//        mSocket.connect()
        val obj = JSONObject()
        obj.put("phone", getActiveUser()!!.phone)
        mSocket.emit("update_sio", obj)
        mSocket.on("is_online", onConnect)
        handleNewMessage()
    }

    override fun handleNewMessage() {
        val onMessage = Emitter.Listener { args ->
            val data = args[0].toString().split("__SEP__")
            try {
                AsyncTask.execute {
                    var user = dataController.getUserByPhone(data[1])
                    if (user == null) {
                        user = networkService.getUser(data[1])
                        if (user == null) {
                            return@execute
                        }
                    }
                    dataController.updateUser(user)
                    val lastMessage = ChatMessage(
                        data[2], user.name,
                        data[0], Date(), user, user.dbId
                    )
                    dataController.updateMessage(lastMessage)
                    val dialog = ChatDialog(
                        user.phone, "", 0, lastMessage, "",
                        mutableListOf(user)
                    )
                    dataController.saveDialog(dialog)
                }
            } catch (e: Exception) {
            }
        }
        mSocket.on("start_dialog", onMessage)
    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as ChatContract.MView
        networkService = Routines(baseView.getCurrentContext())
    }

    override fun attachDataController(view: BaseContract.MView) {
        super.attachDataController(view)
        dataController = ChatDataController(bDataController.dao)
    }
}