package com.chat.bposeats.chat

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface ChatContract {
    interface MView{
        fun loadUsers(data: List<User>?)
    }

    interface MPresenter{
        fun onViewInitialized()
    }

    interface DataController {
        fun bindData(lifeCycleOwner: LifecycleOwner, newDataBlock: (List<User>?) -> (Unit))
    }
}