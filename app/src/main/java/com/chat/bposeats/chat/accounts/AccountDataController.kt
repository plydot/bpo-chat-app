package com.chat.bposeats.chat.accounts;

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage

class AccountDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), AccountsContract.DataController {

    override fun bindUsers(lifeCycleOwner: LifecycleOwner, userData: (List<User>) -> Unit) {
        dao.userDao.getCurrentUser(false).observe(
            lifeCycleOwner,
            Observer { o -> userData.invoke(o.toMutableList()) }
        )
    }
}