package com.chat.bposeats.chat.messages

import com.chat.bposeats.architecture.base.BasePresenter

class ChatMessagesPresenter: BasePresenter(), ChatMessagesContract.MPresenter {
    private lateinit var mView: ChatMessagesContract.MView
    private lateinit var dataController: ChatMessagesDataController

    override fun onViewInitialized() {
        getNewMessages()
    }

    override fun getNewMessages() {
        dataController.bindChatMessages(
            baseView.getLifeCycleOwnerInstance(),
            mView::updateMessageList
        )
    }
}