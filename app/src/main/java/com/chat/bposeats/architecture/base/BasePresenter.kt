package com.chat.bposeats.architecture.base

import androidx.lifecycle.ViewModel
import com.chat.bposeats.BPChatApp
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.chat.ChatDataController
import com.chat.bposeats.data.data.entity.User
import com.github.nkzawa.socketio.client.Socket

open class BasePresenter : ViewModel(), BaseContract.MPresenter {

    lateinit var baseView: BaseContract.MView
    lateinit var daoFactory: DaoFactory
    lateinit var bDataController: BaseDataController


    override fun attachView(view: BaseContract.MView) {
        baseView = view
        attachDataController(view)
    }

    override fun attachDataController(view: BaseContract.MView) {
        daoFactory = BPChatApp.daoFactory(baseView.application() as BPChatApp)
        bDataController = BaseDataController(daoFactory)
    }

    override fun getActiveUser() : User? {
        return bDataController.getActiveUser()
    }

    override fun getSocket(): Socket {
        val app: BPChatApp = baseView.getCurrentContext().application as BPChatApp
        return app.mSocket()!!
    }

}