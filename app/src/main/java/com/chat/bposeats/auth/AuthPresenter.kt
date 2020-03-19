package com.chat.bposeats.auth

import android.os.AsyncTask
import android.view.View
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.chat.bposeats.data.data.entity.*
import com.chat.bposeats.data.network.Routines
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthPresenter: BasePresenter(), AuthContract.MPresenter {

    private lateinit var dataController: AuthDataController
    private lateinit var mView: AuthContract.MView
    private lateinit var networkService: Routines
    override fun signUp() {
        if (mView.getPhoneNumber().isEmpty()) {
            mView.showAuthError("Phone number required")
            return
        }
        AsyncTask.execute {
            val isRegistered = isPhoneRegistered()
            if ((mView.isSignRequest() && isRegistered) || (!mView.isSignRequest() && !isRegistered)){
                processFirebasePhoneAuth()
            }else{
                if (mView.isSignRequest()){
                    mView.showAuthError("Phone number not registered, Sign up to continue")
                }else{
                    mView.showAuthError("Phone number already registered, Sign in to continue")
                }
            }
        }

    }

    override fun signIn() {
        AsyncTask.execute {
            if (mView.isSignRequest()) {
                saveUser()
            } else {
                val credentials = RegisterData(
                    mView.getPhoneNumber().replace("+", ""),
                    "${mView.getPhoneNumber().replace("+", "").toInt() * 2}",
                    mView.getFirstName(),
                    mView.getLastName()
                )
                val authStatus: AuthResponse? = networkService.register(credentials)

                if (authStatus != null) {
                    if (authStatus.errors.isNotEmpty()) {
                        var errr = ""
                        for (err: String in authStatus.errors) {
                            errr += err
                        }
                        mView.showAuthError(errr)
                    } else {
                        if (authStatus.data.success) {
                            saveUser()
                        } else {
                            mView.showAuthError("Service not available")
                        }
                    }
                } else {
                    mView.showAuthError("Service not available")
                }
            }
        }
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

    override fun isPhoneRegistered() : Boolean {
        val isRegistered: Boolean? =
            networkService.checkRegistration(mView.getPhoneNumber().replace("+", ""))
                ?: return false
        return isRegistered!!
    }

    override fun getJwtToken(phone: String) {
        val token : JwtToken? = networkService.getToken(Auth(phone, "${phone.toInt() * 2}"))
        if (token != null){
            dataController.saveJwtToken(token)
        }
    }

    private fun saveUser() {
        val phone: String = mView.getPhoneNumber().replace("+", "")
        val user = networkService.getUser(phone)
        // get access token and save it to db
        getJwtToken(phone)
        if (user != null) {
            dataController.addActiveUser(user, mView::close)
        } else {
            mView.showAuthError("Service not available")
        }
    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as AuthContract.MView
        networkService = Routines(baseView.getCurrentContext())
    }

    override fun attachDataController(view: BaseContract.MView) {
        super.attachDataController(view)
        dataController = AuthDataController(bDataController.dao)
    }
}