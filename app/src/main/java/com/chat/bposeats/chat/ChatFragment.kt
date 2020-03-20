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
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : BaseFragment(), ChatContract.MView {

    private lateinit var mPresenter: ChatPresenter
    private lateinit var dialogsListAdapter: DialogsListAdapter<IDialog<IMessage>>

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


    override fun loadUsers(data: List<User>?) {
        Toast.makeText(activity!!, data.toString(), Toast.LENGTH_LONG).show()
    }

    override fun getActiveUser(user: List<User>?) {
        mPresenter.logInUser(user)
    }

    override fun onResume() {
        super.onResume()
        try {
            mPresenter.connectSocket(this::connectStatus)
        }catch (e: Exception){}
    }

    override fun displayLoginUi() {
        findNavController().navigate(R.id.action_ChatFragment_to_AuthFragment)
    }

    override fun displayChatUi() {
        //connect to socket server
        mPresenter.connectSocket(this::connectStatus)

        val emptyDialog = mutableListOf<IDialog<IMessage>>()
        dialogsListAdapter =
            DialogsListAdapter<IDialog<IMessage>>(ImageLoader { imageView, url, payload ->
                try {
                    Picasso.get().load(url).into(imageView)
                } catch (e: IllegalArgumentException) {
                }
            })
        dialogsListAdapter.addItems(emptyDialog)
        //attach click listeners
        attachedDialogListeners(dialogsListAdapter)
        dialogsList.setAdapter(dialogsListAdapter)
        //update dialog lists
        mPresenter.getDialogs()
    }

    override fun updateDialog(dialogs: List<IDialog<IMessage>>) {
        dialogsListAdapter.clear()
        dialogsListAdapter.addItems(dialogs)
    }

    override fun getDialogAdapter(): DialogsListAdapter<IDialog<IMessage>> = dialogsListAdapter

    override fun attachedDialogListeners(dialogsListAdapter: DialogsListAdapter<IDialog<IMessage>>) {
        dialogsListAdapter.setOnDialogClickListener { dialog ->
            run {
                mPresenter.loadDialogUsers(dialog, this::loadDialogUsers)
            }
        }
    }

    override fun loadDialogUsers(users: List<String>) {
        val action = ChatFragmentDirections.actionChatFragmentToChatMessagesFragment()
        action.userIds = users.toTypedArray()
        findNavController().navigate(action)
    }

    override fun connectStatus(sio: String?) {
        activity!!.runOnUiThread {
            if (sio != null && sio != "None") {
                try {
                    start_dialog.visibility = View.VISIBLE
                    start_dialog.setOnClickListener {
                        findNavController().navigate(R.id.action_ChatFragment_to_AccountFragment)
                    }
                }catch (e: Exception){}
            } else {
                Toast.makeText(activity!!, "Not authorised", Toast.LENGTH_LONG).show()
                start_dialog.visibility = View.GONE
                displayLoginUi()
            }
        }
    }


}
