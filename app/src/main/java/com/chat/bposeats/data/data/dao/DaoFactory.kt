package com.chat.bposeats.data.data.dao

import android.content.Context
import com.chat.bposeats.data.data.AppDatabase

class DaoFactory(application: Context) {
    private var db: AppDatabase = AppDatabase.invoke(application)
    var userDao: UserDao

    init {
        userDao = db.userDao()
    }

}