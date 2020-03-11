package com.chat.bposeats.architecture.base.builder

import com.chat.bposeats.view.MainActivity
import com.chat.bposeats.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

}