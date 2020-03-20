package com.chat.bposeats.chat.messages

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.github.nkzawa.emitter.Emitter
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.stfalcon.chatkit.messages.MessagesListAdapter

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface ChatMessagesContract {
    interface MView {
        fun initializeAdapter()
        fun updateMessageList(messages: MutableList<IMessage>)
        fun getViewArguments(): Bundle?
        fun attachInputListener(adapter: MessagesListAdapter<IMessage>)
        fun updateWithNewMessage(message: IMessage)
    }

    interface MPresenter {
        fun onViewInitialized()
        fun getNewMessages(users: List<String>?)
        fun addNewMessage(message: String, user: User)
        fun sendNewMessage(message: String, phone: String)
        fun messageEmitter() : Emitter.Listener
    }

    interface DataController {
        fun bindChatMessages(
            lifeCycleOwner: LifecycleOwner,
            users: List<String>,
            dialogData: (MutableList<IMessage>) -> (Unit)
        )

        fun saveNewChatMessage(message: String, user: User, out: (IMessage) -> (Unit))

    }
}