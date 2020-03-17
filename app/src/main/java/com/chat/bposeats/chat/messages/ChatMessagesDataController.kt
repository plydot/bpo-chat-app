package com.chat.bposeats.chat.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IMessage
import java.util.*

class ChatMessagesDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), ChatMessagesContract.DataController  {
    override fun bindChatMessages(
        lifeCycleOwner: LifecycleOwner,
        users: List<String>,
        dialogData: (MutableList<IMessage>) -> Unit
    ) {
        dao.chatMessageDao.dialogMessages(users).observe(
            lifeCycleOwner,
            Observer { o -> dialogData.invoke(o.toMutableList()) }
        )
    }

    override fun saveNewChatMessage(message: String, user: User, out: (IMessage) -> Unit) {
        val lastMessage = ChatMessage(UUID.randomUUID().toString(), user.name,
            message, Date(), user, user.dbId
        )
        dao.chatMessageDao.insert(lastMessage)
        out.invoke(lastMessage)
    }
}