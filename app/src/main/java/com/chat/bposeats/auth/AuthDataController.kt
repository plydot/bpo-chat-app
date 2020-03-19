package com.chat.bposeats.auth

import com.chat.bposeats.architecture.base.BaseDataController
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.User
import java.util.*

class AuthDataController(daoFactory: DaoFactory) : BaseDataController(daoFactory = daoFactory), AuthContract.DataController {
    override fun addActiveUser(user: User, reload: () -> Unit) {
        //delete all current entries
        dao.userDao.delete()
        // add an new user record
        dao.userDao.insert(user)
        reload.invoke()
    }
}