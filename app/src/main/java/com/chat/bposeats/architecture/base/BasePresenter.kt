package com.chat.bposeats.architecture.base

import androidx.lifecycle.ViewModel
import com.chat.bposeats.BPChatApp
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.chat.ChatDataController

open class BasePresenter : ViewModel(), BaseContract.MPresenter {

    lateinit var baseView: BaseContract.MView
    lateinit var daoFactory: DaoFactory
    lateinit var mDataController: ChatDataController

    override fun attachView(view: BaseContract.MView) {
        baseView = view
        attachDataController(view)
    }

    override fun attachDataController(view: BaseContract.MView) {
        daoFactory = BPChatApp.daoFactory(baseView.application() as BPChatApp)
        mDataController = ChatDataController(daoFactory)
    }

}