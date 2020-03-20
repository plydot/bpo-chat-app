package com.chat.bposeats.architecture.base

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.github.nkzawa.socketio.client.Socket

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface BaseContract {
    interface MView{
        fun getLifeCycleOwnerInstance(): LifecycleOwner
        fun application(): Application
        fun getCurrentContext(): FragmentActivity
    }

    interface MPresenter{
        fun attachView(view: MView)
        fun attachDataController(view: MView)
        fun getActiveUser() : User?
        fun getSocket() : Socket
    }

    interface DataController{
        fun getActiveUser() : User?
        fun getUserByPhone(phone: String) : User?
        fun saveDialog(dialog: ChatDialog)
        fun updateUser(users: User)
        fun updateMessage(message: ChatMessage)
    }
}