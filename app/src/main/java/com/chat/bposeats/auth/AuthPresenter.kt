package com.chat.bposeats.auth

import android.view.View
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthPresenter: BasePresenter(), AuthContract.MPresenter {

    private lateinit var mView: AuthContract.MView
    override fun signUp() {
        mView.setFirebaseAuth()
        if (mView.getPhoneNumber().isEmpty()){
            mView.showAuthError("Phone number required")
        }else {
            processFirebasePhoneAuth()
        }
    }

    override fun signIn(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processFirebasePhoneAuth() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mView.getPhoneNumber(), // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            baseView.getCurrentContext(), // Activity (for callback binding)
            mView.getVerifyCallback()) // OnVerificationStateChangedCallbacks
    }

    override fun verifyAuthCode(code: String, verificationId: String?) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        mView.signInWithPhoneAuthCredential(credential)
    }

    override fun isAuthCodeVerified(task: Task<AuthResult>): Boolean? {
        return if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            true
        } else {
            // Sign in failed, display a message and update the UI
            if (task.exception is FirebaseAuthInvalidCredentialsException) {
                // The verification code entered was invalid
                false
            }else{
                null
            }
        }
    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as AuthContract.MView
    }
}