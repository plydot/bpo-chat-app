package com.chat.bposeats.auth

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : BaseFragment(), AuthContract.MView {

    private lateinit var mPresenter: AuthPresenter
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = ViewModelProvider(this).get(AuthPresenter::class.java)
        mPresenter.attachView(this)

        //handle on click for sign up
        btn_signup.setOnClickListener {
            mPresenter.signUp()
        }
    }

    override fun setFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    override fun getFirebaseAuth(): FirebaseAuth {
        return auth
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
                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        showAuthError(getString(R.string.invalid_firebase_auth))
                    }
                    is FirebaseTooManyRequestsException -> {
                        showAuthError(getString(R.string.quota_expired))
                    }
                    else -> {
                        showAuthError(e.message!!)
                    }
                }
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                // Save verification ID and resending token so we can use them later
                showVerifyAuthCodeUI(verificationId)
                Toast.makeText(activity!!, "$verificationId : $token", Toast.LENGTH_LONG).show()
            }
        }
        return callbacks
    }

    override fun showAuthError(error: String) {
        Toast.makeText(activity!!, error, Toast.LENGTH_LONG).show()
    }

    override fun showVerifyAuthCodeUI(verificationId: String) {
        // custom dialog
        val dialog = Dialog(activity!!)
        dialog.setContentView(R.layout.verify_code)
        dialog.setTitle(getString(R.string.code_verification))
        val verifyTextView = dialog.findViewById<EditText>(R.id.input_verify_code)
        val verifyButton = dialog.findViewById<Button>(R.id.btn_verify_code)
        verifyButton.setOnClickListener {
            Toast.makeText(activity!!, verifyTextView.text.toString(), Toast.LENGTH_LONG).show()
            mPresenter.verifyAuthCode(verificationId, verifyTextView.text.toString())
        }
        dialog.show();
    }

    override fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity!!) { task ->
                Toast.makeText(activity!!, task.isSuccessful.toString(), Toast.LENGTH_LONG).show()
                val success: Boolean? = mPresenter.isAuthCodeVerified(task)
                if (success != null){
                    if (success){
                        //TODO LOGIN
                        Toast.makeText(activity!!, task.result!!.user!!.phoneNumber, Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(activity!!, getString(R.string.invalid_verification_code), Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(activity!!, getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
                }
            }
    }

}
