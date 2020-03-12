package com.chat.bposeats.chat;

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.User

class ChatDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), ChatContract.DataController {

    override fun bindData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<User>?) -> Unit) {
        dao.userDao.getLiveData().observe(
            lifeCycleOwner,
            Observer<List<User>> { data -> newDataBlock.invoke(data.toMutableList()) }
        )
    }
}