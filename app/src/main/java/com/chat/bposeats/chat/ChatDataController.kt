package com.chat.bposeats.chat;

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.User

class ChatDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), ChatContract.DataController {

    override fun bindChatData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<User>?) -> Unit) {
        dao.userDao.getLiveData().observe(
            lifeCycleOwner,
            Observer { data -> newDataBlock.invoke(data.toMutableList()) }
        )
    }

    override fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> Unit) {
        dao.userDao.getCurrentUser(true).observe(
            lifeCycleOwner,
            Observer { o -> userData.invoke(o.toMutableList()) }
        )
    }
}