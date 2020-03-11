package com.chat.bposeats.architecture.base.presenter

import com.chat.bposeats.architecture.base.interactor.MVPInteractor
import com.chat.bposeats.architecture.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}