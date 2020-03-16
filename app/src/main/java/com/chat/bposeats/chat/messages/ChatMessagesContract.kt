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

    }

    interface MPresenter{
        fun onViewInitialized()
    }

    interface DataController {
        fun bindChatMessages(lifeCycleOwner: LifecycleOwner, dialogData: (List<IMessage>) -> (Unit))
    }
}