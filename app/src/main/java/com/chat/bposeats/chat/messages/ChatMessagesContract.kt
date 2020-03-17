package com.chat.bposeats.chat.messages

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface ChatMessagesContract {
    interface MView {
        fun initializeAdapter()
        fun updateMessageList(messages: MutableList<IMessage>)
        fun getViewArguments(): Bundle?
    }

    interface MPresenter {
        fun onViewInitialized()
        fun getNewMessages(users: List<String>?)
    }

    interface DataController {
        fun bindChatMessages(
            lifeCycleOwner: LifecycleOwner,
            users: List<String>,
            dialogData: (MutableList<IMessage>) -> (Unit)
        )
    }
}