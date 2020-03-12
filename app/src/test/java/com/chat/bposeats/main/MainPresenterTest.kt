package com.chat.bposeats.main

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private lateinit var mView: MainContract.MView
    private lateinit var mPresenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = MainPresenter(mView)
    }

    @Test
    fun handleSignIn() {
        mPresenter.HandleSignIn(mView)
        Mockito.verify(mView).showSignInView()
    }

    @Test
    fun handleSignOut() {
        mPresenter.HandleSignOut(mView)
        Mockito.verify(mView).showSignOutView()
    }
}