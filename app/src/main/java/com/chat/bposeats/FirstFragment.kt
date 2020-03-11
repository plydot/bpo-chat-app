package com.chat.bposeats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONArray
import org.json.JSONObject


class FirstFragment : Fragment() {

    private lateinit var mSocket: Socket;
    private lateinit var lSocket: Socket;
    private var isConnected = true

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            val poisonObject = JSONObject()
            poisonObject.put("data", "message 1")
            mSocket.emit("my_event", poisonObject)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
}
