package com.chat.bposeats.presenter

import com.chat.bposeats.view.MainMVPView
import com.chat.bposeats.architecture.base.presenter.MVPPresenter
import com.chat.bposeats.interator.MainMVPInteractor

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {


}