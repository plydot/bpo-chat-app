package com.chat.bposeats.chat.messages

import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter

class ChatMessagesPresenter: BasePresenter(), ChatMessagesContract.MPresenter {
    private lateinit var mView: ChatMessagesContract.MView
    private lateinit var dataController: ChatMessagesDataController

    override fun onViewInitialized() {
        getNewMessages()

    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as ChatMessagesContract.MView
    }

    override fun attachDataController(view: BaseContract.MView) {
        super.attachDataController(view)
        dataController = ChatMessagesDataController(bDataController.dao)
    }

    override fun getNewMessages() {
        dataController.bindChatMessages(
            baseView.getLifeCycleOwnerInstance(),
            mView::updateMessageList
        )
    }
}