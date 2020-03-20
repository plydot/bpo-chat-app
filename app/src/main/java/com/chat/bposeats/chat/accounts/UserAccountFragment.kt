package com.chat.bposeats.chat.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.chat.bposeats.chat.ChatFragmentDirections
import com.chat.bposeats.chat.ChatPresenter
import com.chat.bposeats.data.data.entity.User
import kotlinx.android.synthetic.main.fragment_user_account_list.*


class UserAccountFragment : BaseFragment(), AccountsContract.MView {

    private lateinit var adapter: UserAccountRecyclerViewAdapter
    private lateinit var mPresenter: AccountPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_account_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //initialize presenter
        mPresenter = ViewModelProvider(this).get(AccountPresenter::class.java)
        //attach view to presenter
        mPresenter.attachView(this)

        //initialize adapter
        adapter = UserAccountRecyclerViewAdapter(mPresenter)
        user_list.adapter = adapter

        //initialize chat ui
        mPresenter.onViewInitialized()
        //update with online repo
        mPresenter.updateUsers()
    }

    override fun loadUsers(data: List<User>) {
        adapter.setData(data)
    }

    override fun loadDialogUsers(users: List<String>) {
        val action = ChatFragmentDirections.actionChatFragmentToChatMessagesFragment()
        action.userIds = users.toTypedArray()
        findNavController().navigate(action)
    }
}
