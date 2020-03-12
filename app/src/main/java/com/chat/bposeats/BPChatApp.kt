package com.chat.bposeats

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket


class BPChatApp: MultiDexApplication() {

    private lateinit var mSocket: Socket;
    private lateinit var lSocket: Socket;

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        mSocket = IO.socket("https://b3c62fa1.ngrok.io")
        lSocket = IO.socket("https://b3c62fa1.ngrok.io")
    }

    fun mSocket(): Socket? {
        return mSocket
    }

    fun lSocket(): Socket?{
        return lSocket
    }
}