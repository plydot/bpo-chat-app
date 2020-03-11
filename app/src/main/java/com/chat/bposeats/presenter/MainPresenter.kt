package com.chat.bposeats.presenter;

import com.chat.bposeats.architecture.base.presenter.BasePresenter
import com.chat.bposeats.interator.MainMVPInteractor
import com.chat.bposeats.utils.SchedulerProvider
import com.chat.bposeats.view.MainMVPView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject;

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        MainMVPPresenter<V, I> {

        override fun onAttach(view:V?){
        super.onAttach(view)

        }
}