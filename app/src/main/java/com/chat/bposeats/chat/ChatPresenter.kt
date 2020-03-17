package com.chat.bposeats.chat

import android.widget.Toast
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.chat.bposeats.data.data.entity.User
import com.chat.bposeats.utils.Constants
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage

class ChatPresenter: BasePresenter(), ChatContract.MPresenter {

    private lateinit var mView: ChatContract.MView
    private lateinit var dataController: ChatDataController

    override fun onViewInitialized() {
        dataController.bindActiveUser(
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

    override fun getDialogs() {
        dataController.bindChatDialogs(
            baseView.getLifeCycleOwnerInstance(),
            mView::updateDialog
        )
    }

    override fun loadDialogUsers(dialog: IDialog<IMessage>, out: (List<String>) -> (Unit)) {
        val userIds = mutableListOf<String>()
        for (user in dialog.users){
            userIds.add(user.id)
        }
        userIds.add(getActiveUser()!!.dbId)
        out.invoke(userIds)
    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as ChatContract.MView
    }

    override fun attachDataController(view: BaseContract.MView) {
        super.attachDataController(view)
        dataController = ChatDataController(bDataController.dao)
    }
}