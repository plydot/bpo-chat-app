package com.chat.bposeats.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chat.bposeats.BPChatApp
import com.chat.bposeats.R
import com.chat.bposeats.architecture.base.BaseFragment
import com.chat.bposeats.data.data.entity.User
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import java.util.*


class ChatFragment : BaseFragment(), ChatContract.MView {

    private lateinit var mSocket: Socket;
    private lateinit var lSocket: Socket;
    private var isConnected = true
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

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            //            val poisonObject = JSONObject()
//            poisonObject.put("data", "message 1")
//            mSocket.emit("my_event", poisonObject)
            val daoFactory = BPChatApp.daoFactory(activity!!.application as BPChatApp)
            daoFactory.userDao.insert(User(UUID.randomUUID().toString(), "test 2"))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter = ViewModelProvider(this).get(ChatPresenter::class.java)
        mPresenter.attachView(this)
        mPresenter.onViewInitialized()

        val app: BPChatApp = activity!!.application as BPChatApp
        mSocket = app.mSocket()!!
        mSocket.connect()
        lSocket = app.lSocket()!!
        lSocket.on("my_response", onNewMessage);
        lSocket.connect()
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
}
