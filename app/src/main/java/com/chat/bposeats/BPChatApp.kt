package com.chat.bposeats

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class BPChatApp: MultiDexApplication(), HasActivityInjector {

    private lateinit var mSocket: Socket;
    private lateinit var lSocket: Socket;

    @Inject
    lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
//        DaggerAppComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)
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