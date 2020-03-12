package com.chat.bposeats.architecture.base

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

public open class BaseFragment: Fragment(), BaseContract.MView {
    override fun getLifeCycleOwnerInstance(): LifecycleOwner = this

    override fun application(): Application = activity!!.application
}