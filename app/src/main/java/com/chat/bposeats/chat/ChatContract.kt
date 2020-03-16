package com.chat.bposeats.chat

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface ChatContract {
    interface MView{
        fun loadUsers(data: List<User>?)
        fun getActiveUser(user: List<User>?)
        fun displayLoginUi()
        fun displayChatUi()
        fun updateDialog(dialogs: List<IDialog<IMessage>>)
        fun getDialogAdapter() : DialogsListAdapter<IDialog<IMessage>>
    }

    interface MPresenter{
        fun onViewInitialized()
        fun logInUser(user: List<User>?)
        fun runChat()
        fun getDialogs()
    }

    interface DataController {
        fun bindChatData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<User>?) -> (Unit))
        fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> (Unit))
        fun bindChatDialogs(lifeCycleOwner: LifecycleOwner, dialogData: (List<IDialog<IMessage>>) -> (Unit))
    }
}