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
import androidx.navigation.fragment.findNavController
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
    private var mSignRequest: Boolean = false

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

        link_login.setOnClickListener{
            displaySignInUi()
        }
    }

    override fun setFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    override fun getVerifyCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
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
            }
        }
    }

    override fun showAuthError(error: String) {
        Toast.makeText(activity!!, error, Toast.LENGTH_LONG).show()
    }

    override fun showVerifyAuthCodeUI(verificationId: String) {
        // custom dialog
        val dialog = Dialog(activity!!, android.R.style.Theme_Material_NoActionBar_Fullscreen)
        dialog.setContentView(R.layout.verify_code)
        dialog.setTitle(getString(R.string.code_verification))
        val verifyTextView = dialog.findViewById<EditText>(R.id.input_verify_code)
        val verifyButton = dialog.findViewById<Button>(R.id.btn_verify_code)
        verifyButton.setOnClickListener {
            mPresenter.verifyAuthCode(verifyTextView.text.toString(), verificationId)
        }
        dialog.show();
    }

    override fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity!!) { task ->
                val success: Boolean? = mPresenter.isAuthCodeVerified(task)
                if (success != null){
                    if (success){
                        mPresenter.signIn()
                    }else{
                        Toast.makeText(activity!!, getString(R.string.invalid_verification_code), Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(activity!!, getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun getFirstName() = first_name.text.toString()

    override fun getLastName() = last_name.text.toString()

    override fun getFirebaseAuth() = auth

    override fun getPhoneNumber() = input_mobile.text.toString()

    //closes auth ui and loads default chat ui
    override fun close() {
        findNavController().navigate(R.id.action_AuthFragment_to_ChatFragment)
    }

    override fun displaySignInUi() {

        //set sign note to true
        mSignRequest = true

        first_name.visibility = View.GONE
        last_name.visibility = View.GONE
        btn_signup.text = getString(R.string.sigin_in)
        link_login.text = getString(R.string.not_registered_signup)

        link_login.setOnClickListener{
            //set sign in not to false
            mSignRequest = false
            first_name.visibility = View.VISIBLE
            last_name.visibility = View.VISIBLE
            btn_signup.text = getString(R.string.create_account)
            link_login.text = getString(R.string.already_registered_login)
            link_login.setOnClickListener{
                displaySignInUi()
            }
        }
    }

    override fun isSignRequest() = mSignRequest

}
