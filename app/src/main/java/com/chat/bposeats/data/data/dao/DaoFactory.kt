package com.chat.bposeats.data.data.dao

import android.content.Context
import com.chat.bposeats.data.data.AppDatabase
import com.plydot.sms.bulksms.datastore.dao.UserDao

class DaoFactory(application: Context) {
    private var db: AppDatabase = AppDatabase.invoke(application)
    var userDao: UserDao

    init {
        userDao = db.userDao()
    }

}