package com.chat.bposeats.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment


class AuthFragment : BaseFragment(), AuthContract.MView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun displayFirebasePhoneVerifyUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
