package com.chat.bposeats.main

import android.view.View

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface MainContract {
    interface MView{
        fun showSignInView()
        fun showSignOutView()
    }

    interface MPresenter{
        fun HandleSignIn(view: MView)
        fun HandleSignOut(view: MView)
    }
}