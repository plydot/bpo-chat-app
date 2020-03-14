package com.chat.bposeats.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

public interface AuthContract {
    interface MView{
        fun setFirebaseAuth()
        fun getFirebaseAuth() : FirebaseAuth
        fun getPhoneNumber() : String
        fun getVerifyCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks
        fun showAuthError(error: String)
        fun showVerifyAuthCodeUI(verificationId: String)
        fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)
        fun getFirstName() : String
        fun getLastName(): String
        fun close()
    }

    interface MPresenter{
        fun signUp()
        fun signIn()
        fun processFirebasePhoneAuth()
        fun verifyAuthCode(code: String, verificationId: String?)
        fun isAuthCodeVerified(task: Task<AuthResult>) : Boolean?

    }

    interface DataController {
        fun addActiveUser(name: String, phone: String, reload: () -> (Unit))
    }
}