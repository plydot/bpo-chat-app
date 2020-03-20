package com.chat.bposeats.chat.accounts

import android.os.AsyncTask
import com.chat.bposeats.BPChatApp
import com.chat.bposeats.architecture.base.BaseContract
import com.chat.bposeats.architecture.base.BasePresenter
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.User
import com.chat.bposeats.data.network.Routines
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.github.tamir7.contacts.PhoneNumber
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage


class AccountPresenter : BasePresenter(), AccountsContract.MPresenter {

    public lateinit var mView: AccountsContract.MView
    private lateinit var dataController: AccountsContract.DataController
    private lateinit var networkService: Routines

    override fun onViewInitialized() {
        dataController.bindUsers(
            baseView.getLifeCycleOwnerInstance(),
            mView::loadUsers
        )
    }

    override fun updateUsers() {
        AsyncTask.execute {
            val users = networkService.getUsers(getPhoneBookNumbers())
            if (users != null) {
                for (user: User in users) {
                    user.current = false
                    dataController.updateUser(user)
                }
            }
        }
    }

    override fun getPhoneBookNumbers(): List<String> {
        val contacts: List<Contact> = Contacts.getQuery().find()
        val numbers: MutableList<String> = mutableListOf()
        for (contact: Contact in contacts) {
            for (n: PhoneNumber in contact.phoneNumbers) {
                val number = n.normalizedNumber.replace("+", "")
                if (!numbers.contains(number)) {
                    numbers.add(number)
                }
            }
        }
        return numbers
    }

    override fun loadDialogUsers(dialog: IDialog<IMessage>, out: (List<String>) -> Unit) {
        val userIds = mutableListOf<String>()
        for (user in dialog.users) {
            userIds.add(user.id)
        }
        userIds.add(getActiveUser()!!.dbId)
        out.invoke(userIds)
    }

    override fun startDialog(user: User) {
        val dialog = ChatDialog(
            user.phone, "", 0, null, "",
            mutableListOf(user)
        )
        dataController.saveDialog(dialog)
        loadDialogUsers(dialog, mView::loadDialogUsers)
    }

    override fun attachView(view: BaseContract.MView) {
        super.attachView(view)
        mView = view as AccountsContract.MView
        networkService = Routines(baseView.getCurrentContext())
    }

    override fun attachDataController(view: BaseContract.MView) {
        super.attachDataController(view)
        dataController = AccountDataController(bDataController.dao)
    }
}