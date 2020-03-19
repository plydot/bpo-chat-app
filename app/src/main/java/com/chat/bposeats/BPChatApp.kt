package com.chat.bposeats

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.chat.bposeats.data.data.dao.DaoFactory
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket


class BPChatApp: MultiDexApplication() {

    private lateinit var mSocket: Socket;
    private lateinit var daoFactory: DaoFactory

    override fun onCreate() {
        super.onCreate()
        daoFactory = DaoFactory(applicationContext)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        mSocket = IO.socket("https://7c7731d7.ngrok.io")
    }

    companion object{
        fun daoFactory(a: BPChatApp) = a.daoFactory
    }

    fun mSocket(): Socket? {
        return mSocket
    }
}