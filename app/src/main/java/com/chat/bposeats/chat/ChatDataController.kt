package com.chat.bposeats.chat;

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage

class ChatDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), ChatContract.DataController {

    override fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> Unit) {
        dao.userDao.getCurrentUser(true).observe(
            lifeCycleOwner,
            Observer { o -> userData.invoke(o.toMutableList()) }
        )
    }

    override fun bindChatDialogs(
        lifeCycleOwner: LifecycleOwner,
        dialogData: (List<IDialog<IMessage>>) -> Unit
    ) {
        dao.chatDialogDao.getLiveData().observe(
            lifeCycleOwner,
            Observer { o -> dialogData.invoke(o.toMutableList()) }
        )
    }


}