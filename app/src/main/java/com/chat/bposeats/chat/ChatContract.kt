package com.chat.bposeats.chat

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface ChatContract {
    interface MView{
        fun loadUsers(data: List<User>?)
        fun getActiveUser(user: List<User>?)
        fun displayLoginUi()
        fun displayChatUi()
    }

    interface MPresenter{
        fun onViewInitialized()
        fun logInUser(user: List<User>?)
        fun runChat()
    }

    interface DataController {
        fun bindChatData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<User>?) -> (Unit))
        fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> (Unit))
    }
}