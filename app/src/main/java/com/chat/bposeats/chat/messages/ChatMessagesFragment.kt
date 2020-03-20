package com.chat.bposeats.chat.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.chat.bposeats.data.data.entity.ChatMessage
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.fragment_chat_messages.*

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
        //initialize messages adapter
        initializeAdapter()
    }

    override fun initializeAdapter() {
        messageListAdapter = MessagesListAdapter(mPresenter.getActiveUser()!!.id, null)
        messageListAdapter.addToEnd(emptyList(), false)
        messagesList.setAdapter(messageListAdapter)
        attachInputListener(messageListAdapter)
    }

    override fun updateMessageList(messages: MutableList<IMessage>) {
        messageListAdapter.clear()
        messageListAdapter.addToEnd(messages, true)
    }

    override fun getViewArguments() = arguments

    override fun attachInputListener(adapter: MessagesListAdapter<IMessage>) {
        message_input.setInputListener { input: CharSequence? ->
            kotlin.run {
                for (m in arguments!!.getStringArray("userIds")!!.asList()){
                    val user = mPresenter.getUserById(m)!!
                    Toast.makeText(activity!!, user.phone, Toast.LENGTH_LONG).show()
                    if(user.phone != mPresenter.getActiveUser()!!.phone){
                        mPresenter.sendNewMessage(input.toString(), user.phone)
                        break
                    }
                }
                true
            }
        }
    }

    override fun updateWithNewMessage(message: IMessage) {
        val list = mutableListOf<IMessage>()
        list.add(message)
        messageListAdapter.addToEnd(list, true)
        messageListAdapter.notifyDataSetChanged()
    }

}
