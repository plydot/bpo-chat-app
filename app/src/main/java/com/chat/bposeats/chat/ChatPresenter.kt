package com.chat.bposeats.chat

import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter

class ChatPresenter: BasePresenter(), ChatContract.MPresenter {

    private lateinit var mView: ChatContract.MView

    override fun onViewInitialized() {
        mDataController.bindData(
            baseView.getLifeCycleOwnerInstance(),
            mView::loadUsers
        )
    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as ChatContract.MView
    }
}