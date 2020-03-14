package com.chat.bposeats.auth;

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.User

class AuthDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), AuthContract.DataController {
    override fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}