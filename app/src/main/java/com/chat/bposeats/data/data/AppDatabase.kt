package com.chat.bposeats.data.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chat.bposeats.data.data.dao.UserDao
import com.chat.bposeats.data.data.entity.User

@Database(entities = [User::class], version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "sms.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}