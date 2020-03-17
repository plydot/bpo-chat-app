package com.chat.bposeats.chat.messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.fragment_chat_messages.*

/**
 * A simple [Fragment] subclass.
 */
class ChatMessagesFragment : BaseFragment(), ChatMessagesContract.MView {

    private lateinit var mPresenter: ChatMessagesPresenter
    private lateinit var messageListAdapter: MessagesListAdapter<IMessage>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_messages, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //initialize presenter
        mPresenter = ViewModelProvider(this).get(ChatMessagesPresenter::class.java)
        //attach view to presenter
        mPresenter.attachView(this)
        //initialize chat ui
        mPresenter.onViewInitialized()
    }

    override fun initializeAdapter() {
        messageListAdapter = MessagesListAdapter(mPresenter.getActiveUser()!!.id, null)
        messageListAdapter.addToEnd(emptyList(), false)
        messagesList.setAdapter(messageListAdapter)
    }

    override fun updateMessageList(messages: MutableList<IMessage>) {
        messageListAdapter.addToEnd(messages, false)
    }

}
