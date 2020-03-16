package com.chat.bposeats.chat.messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class ChatMessagesFragment : BaseFragment(), ChatMessagesContract.MView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_messages, container, false)
    }

}
