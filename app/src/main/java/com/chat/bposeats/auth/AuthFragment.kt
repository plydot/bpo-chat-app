package com.chat.bposeats.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : BaseFragment(), AuthContract.MView {

    private lateinit var mPresenter: AuthPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = ViewModelProvider(this).get(AuthPresenter::class.java)
        mPresenter.attachView(this)
    }

    override fun getPhoneNumber() : String {
        return input_mobile.text.toString()
    }

    override fun getVerifyCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                signInWithPhoneAuthCredential(credential)
                Toast.makeText(activity!!, credential.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(activity!!, e.message, Toast.LENGTH_LONG).show()
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }
                // Show a message and update the UI
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                // Save verification ID and resending token so we can use them later
//                storedVerificationId = verificationId
//                resendToken = token
                Toast.makeText(activity!!, "$verificationId : $token", Toast.LENGTH_LONG).show()
            }
        }
        return callbacks
    }

}
