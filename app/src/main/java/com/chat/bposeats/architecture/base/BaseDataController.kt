package com.chat.bposeats.architecture.base

import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import java.lang.Exception

open class BaseDataController(daoFactory: DaoFactory) : BaseContract.DataController {

    internal var dao: DaoFactory = daoFactory
    override fun getActiveUser(): User? {
        return try {
            dao.userDao.getRawCurrentUser(true)[0]
        }catch (e : Exception){
            null
        }
    }

    override fun getUserByPhone(phone: String) : User? {
        return dao.userDao.getUser(phone)
    }

    override fun getUserById(id: String): User? {
        return dao.userDao.getUserById(id)
    }

    override fun saveDialog(dialog: ChatDialog) {
        dao.chatDialogDao.insert(dialog)
    }

    override fun updateUser(users: User) {
        dao.userDao.insert(users)
    }

    override fun updateMessage(message: ChatMessage) {
        dao.chatMessageDao.insert(message)
    }

}