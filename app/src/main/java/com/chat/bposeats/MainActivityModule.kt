package com.chat.bposeats

import com.chat.bposeats.interator.MainInteractor
import com.chat.bposeats.interator.MainMVPInteractor
import com.chat.bposeats.presenter.MainMVPPresenter
import com.chat.bposeats.presenter.MainPresenter
import com.chat.bposeats.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter

}