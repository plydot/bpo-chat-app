package com.chat.bposeats.auth

import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User

public interface AuthContract {
    interface MView{
        fun displayFirebasePhoneVerifyUI()
    }

    interface MPresenter{
        fun processFirebasePhoneAuth()
    }

    interface DataController {
        fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> (Unit))
    }
}