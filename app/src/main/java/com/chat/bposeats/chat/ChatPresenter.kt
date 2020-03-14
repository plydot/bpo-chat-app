package com.chat.bposeats.chat

import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.chat.bposeats.data.data.entity.User

class ChatPresenter: BasePresenter(), ChatContract.MPresenter {

    private lateinit var mView: ChatContract.MView

    override fun onViewInitialized() {
        mDataController.bindActiveUser(
            baseView.getLifeCycleOwnerInstance(),
            mView::getActiveUser
        )
    }

    override fun logInUser(user: List<User>?) {
        if(user == null || user.isEmpty()){
            mView.displayLoginUi()
        }else{
            mView.displayChatUi()
        }
    }

    override fun runChat() {

    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as ChatContract.MView
    }
}