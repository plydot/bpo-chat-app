package com.chat.bposeats.chat.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.stfalcon.chatkit.commons.models.IMessage

class ChatMessagesDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), ChatMessagesContract.DataController  {
    override fun bindChatMessages(
        lifeCycleOwner: LifecycleOwner,
        dialogData: (MutableList<IMessage>) -> Unit
    ) {
        dao.chatMessageDao.getLiveData().observe(
            lifeCycleOwner,
            Observer { o -> dialogData.invoke(o.toMutableList()) }
        )
    }
}