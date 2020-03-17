package com.chat.bposeats.chat.messages

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface ChatMessagesContract {
    interface MView{
        fun initializeAdapter()
        fun updateMessageList(messages: MutableList<IMessage>)
    }

    interface MPresenter{
        fun onViewInitialized()
        fun getNewMessages()
    }

    interface DataController {
        fun bindChatMessages(lifeCycleOwner: LifecycleOwner, dialogData: (MutableList<IMessage>) -> (Unit))
    }
}