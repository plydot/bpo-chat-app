package com.chat.bposeats.architecture.base

import android.app.Activity
import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.chat.bposeats.data.data.entity.User

/**
 * Defines contract between th view {@link MainActivity} and presenter
 */
public interface BaseContract {
    interface MView{
        fun getLifeCycleOwnerInstance(): LifecycleOwner
        fun application(): Application
        fun getCurrentContext(): FragmentActivity
    }

    interface MPresenter{
        fun attachView(view: MView)
        fun attachDataController(view: MView)
        fun getActiveUser() : User?
    }

    interface DataController{
        fun getActiveUser() : User?
    }
}