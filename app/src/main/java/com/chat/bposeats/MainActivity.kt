package com.chat.bposeats

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.ChatMessage
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * Displays the main screen
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //request for contacts permissions
        val dialogPermissionListener: PermissionListener = DialogOnDeniedPermissionListener.Builder
            .withContext(this)
            .withTitle("Contacts permission")
            .withMessage("Contacts permission is needed to sync your chats")
            .withButtonText(android.R.string.ok)
            .build()

        Dexter.withActivity(this)
            .withPermission(Manifest.permission.READ_CONTACTS)
            .withListener(dialogPermissionListener)
            .check()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val dao = BPChatApp.daoFactory(application as BPChatApp)
                val dbUser = dao.userDao.getRawCurrentUser(true)[0]//User(UUID.randomUUID().toString(), "Jimmy", false, "+256754640680", "Jimmy", "" )
//                dao.userDao.insert(dbUser)
                val lastMessage = ChatMessage(UUID.randomUUID().toString(), "", "Ok!", Date(), dbUser, dbUser.dbId)
                dao.chatMessageDao.insert(lastMessage)
                dao.chatDialogDao.insert(
                    ChatDialog(
                        UUID.randomUUID().toString(),
                        "",
                        1,
                        lastMessage,
                        "",
                        mutableListOf(dbUser)
                    )
                )
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
