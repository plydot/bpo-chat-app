package com.chat.bposeats.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.chat.bposeats.chat.messages.ChatMessagesFragment
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.data.data.entity.User
import com.chat.bposeats.utils.Constants
import com.github.nkzawa.emitter.Emitter
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.stfalcon.chatkit.dialogs.DialogsListAdapter.OnDialogLongClickListener
import kotlinx.android.synthetic.main.fragment_chat.*
import java.io.Serializable
import java.lang.IllegalArgumentException


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
        val emptyDialog = mutableListOf<IDialog<IMessage>>()
        dialogsListAdapter =
            DialogsListAdapter<IDialog<IMessage>>(ImageLoader { imageView, url, payload ->
                try {
                    Picasso.get().load(url).into(imageView)
                }catch (e: IllegalArgumentException){}
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


}
