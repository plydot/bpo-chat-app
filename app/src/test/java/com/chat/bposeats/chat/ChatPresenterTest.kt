package com.chat.bposeats.chat

import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ChatPresenterTest {

    @Mock
    private lateinit var mView: ChatContract.MView
    private lateinit var mPresenter: ChatPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = ChatPresenter()
    }

    @Test
    fun handleSignIn() {
//        mPresenter.HandleSignIn(mView)
//        Mockito.verify(mView).showSignInView()
    }

    @Test
    fun handleSignOut() {
//        mPresenter.HandleSignOut(mView)
//        Mockito.verify(mView).showSignOutView()
    }
}