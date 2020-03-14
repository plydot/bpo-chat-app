package com.chat.bposeats.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.chat.bposeats.data.data.entity.User
import com.github.nkzawa.emitter.Emitter


class ChatFragment : BaseFragment(), ChatContract.MView {

    private lateinit var mPresenter: ChatPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //initialize presenter
        mPresenter = ViewModelProvider(this).get(ChatPresenter::class.java)
        //attach view to presenter
        mPresenter.attachView(this)
        //initialize chat ui
        mPresenter.onViewInitialized()
    }

    private val onNewMessage = Emitter.Listener { args ->
        activity!!.runOnUiThread {
            val data = args[0].toString()
            Toast.makeText(activity!!, data, Toast.LENGTH_LONG).show()
        }
    }

    override fun loadUsers(data: List<User>?) {
        Toast.makeText(activity!!, data.toString(), Toast.LENGTH_LONG).show()
    }

    override fun getActiveUser(user: List<User>?) {
        mPresenter.logInUser(user)
    }

    override fun displayLoginUi() {
        findNavController().navigate(R.id.action_ChatFragment_to_AuthFragment)
    }

    override fun displayChatUi() {
        Toast.makeText(activity!!, "now chatting", Toast.LENGTH_LONG).show()
    }
}
