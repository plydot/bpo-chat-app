package com.chat.bposeats.auth

import com.chat.bposeats.data.data.entity.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

public interface AuthContract {
    interface MView{
        // returns the phone number entered in the login/registration form
        fun getPhoneNumber() : String
        // returns a callback instance used to get the Firebase auth verification code
        fun getVerifyCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks
        // displays the authentication error encountered by firebase during phone number verification
        fun showAuthError(error: String)
        // displays UI to enter send phone number verification received via SMS
        fun showVerifyAuthCodeUI(verificationId: String)
        // signs in to Firebase with the verified PhoneCredential generated from firebase sent code
        fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)
        fun getFirstName() : String
        fun getLastName(): String
        // ends auth and take user to chat room.
        fun close()
        // displays the signin/signup interface
        fun displaySignInUi()
        // returns true if the user is trying to sign, and false for register
        fun isSignRequest(): Boolean

    }

    interface MPresenter{
        // checks if user's phone number is registered/not and triggers phone verification
        fun signUp()
        // sets the system active user after verification
        fun signIn()
        // triggers sending of Firebase phone verification code
        fun processFirebasePhoneAuth()
        // verifies SMS code received on device
        fun verifyAuthCode(code: String, verificationId: String?)
        // returns true if the verification was successful
        fun isAuthCodeVerified(task: Task<AuthResult>) : Boolean?

        fun isPhoneRegistered() : Boolean

    }

    interface DataController {
        // saves active user to db and triggers UI updates
        fun addActiveUser(user: User, reload: () -> (Unit))
    }
}