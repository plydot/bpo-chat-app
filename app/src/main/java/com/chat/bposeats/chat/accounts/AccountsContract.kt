package com.chat.bposeats.chat.accounts

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter

public interface AccountsContract {
    interface MView{
        fun loadUsers(data: List<User>)
    }

    interface MPresenter{
        // initializes view references to the actively logged user if any
        fun onViewInitialized()
        fun updateUsers()
        fun getPhoneBookNumbers() : List<String>
    }

    interface DataController {
        // gets active user from db and trigger ui updates
        fun bindUsers(lifeCycleOwner: LifecycleOwner, userData: (List<User>) -> (Unit))
        fun updateUser(users: User)
    }
}