package com.chat.bposeats.auth

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User
import com.google.firebase.auth.PhoneAuthProvider

public interface AuthContract {
    interface MView{
        fun getPhoneNumber() : String
        fun getVerifyCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks
    }

    interface MPresenter{
        fun signUp(view: View)
        fun signIn(view: View)
        fun processFirebasePhoneAuth()
    }

    interface DataController {
        fun bindActiveUser(lifeCycleOwner: LifecycleOwner, userData: (List<User>?) -> (Unit))
    }
}