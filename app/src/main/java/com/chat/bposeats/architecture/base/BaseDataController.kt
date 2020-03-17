package com.chat.bposeats.architecture.base

import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.User
import java.lang.Exception

open class BaseDataController(daoFactory: DaoFactory) : BaseContract.DataController {

    internal var dao: DaoFactory = daoFactory
    override fun getActiveUser(): User? {
        return try {
            dao.userDao.getCurrentUser(true).value!![0]
        }catch (e : Exception){
            null
        }
    }

}