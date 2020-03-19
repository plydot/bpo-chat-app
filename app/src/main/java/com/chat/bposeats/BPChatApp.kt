package com.chat.bposeats

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.chat.bposeats.data.data.dao.DaoFactory
import com.chat.bposeats.utils.Constants
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket


class BPChatApp: MultiDexApplication() {

    private lateinit var mSocket: Socket
    private lateinit var daoFactory: DaoFactory

    override fun onCreate() {
        super.onCreate()
        daoFactory = DaoFactory(applicationContext)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        mSocket = IO.socket(Constants.DEFAULT_SOCKET_URL)
    }

    companion object{
        fun daoFactory(a: BPChatApp) = a.daoFactory
    }

    fun mSocket(): Socket? {
        return mSocket
    }
}