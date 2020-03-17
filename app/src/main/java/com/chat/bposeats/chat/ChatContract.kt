package com.chat.bposeats.chat

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.ChatMessage
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
        // loads the currently logged in user to the UI
        fun getActiveUser(user: List<User>?)
        //display login fields to allowed logged out user to check n
        fun displayLoginUi()
        // initializes the chat dialog adapter
        fun displayChatUi()
        // adds dialogs to the chat adapter
        fun updateDialog(dialogs: List<IDialog<IMessage>>)
        // getter for the chat dialog adapter
        fun getDialogAdapter() : DialogsListAdapter<IDialog<IMessage>>
        // attaches click listeners to dialog adapter
        fun attachedDialogListeners()

        fun loadDialogMessages(messages: MutableList<ChatMessage>)
    }

    interface MPresenter{
        // initializes view references to the actively logged user if any
        fun onViewInitialized()
        // logs in authorized user
        fun logInUser(user: List<User>?)
        // populates dialog adapter with new chat dialogs
        fun getDialogs()
        //load dialog messages
        fun loadDialogMessages(dialog: IDialog<IMessage>)
    }

    interface DataController {
        // gets active user from db and trigger ui updates
        fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> (Unit))
        // gets new dialogs from the db and triggers ui updates
        fun bindChatDialogs(lifeCycleOwner: LifecycleOwner, dialogData: (List<IDialog<IMessage>>) -> (Unit))

        fun getDialogMessages(userIds : List<String>, out: (MutableList<ChatMessage>) -> (Unit))

    }
}