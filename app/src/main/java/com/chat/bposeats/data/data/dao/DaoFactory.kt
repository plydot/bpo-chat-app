package com.chat.bposeats.data.data.dao

import android.content.Context
import com.chat.bposeats.data.data.AppDatabase

class DaoFactory(application: Context) {
    private var db: AppDatabase = AppDatabase.invoke(application)
    var userDao: UserDao
    var chatMessageDao : ChatMessageDao
    var chatDialogDao : ChatDialogDao
    var jwtDao : JwtDao

    init {
        userDao = db.userDao()
        chatMessageDao = db.chatMessageDao()
        chatDialogDao = db.chatDialogDao()
        jwtDao = db.jwtDao()
    }

}