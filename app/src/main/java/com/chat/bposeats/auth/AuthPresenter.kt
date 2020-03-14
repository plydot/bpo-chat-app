package com.chat.bposeats.auth

import android.view.View
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthPresenter: BasePresenter(), AuthContract.MPresenter {

    private lateinit var mView: AuthContract.MView
    override fun signUp(view: View) {
        processFirebasePhoneAuth()
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

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as AuthContract.MView
    }
}