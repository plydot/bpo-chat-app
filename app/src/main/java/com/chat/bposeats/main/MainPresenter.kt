package com.chat.bposeats.main

import android.view.View
import android.widget.Toast

class MainPresenter(view: MainContract.MView): MainContract.MPresenter {

    private var mView: MainContract.MView = view
    override fun HandleSignIn(view: MainContract.MView) {
        mView.showSignInView()
    }

    override fun HandleSignOut(view: MainContract.MView) {
        mView.showSignOutView()
    }

}