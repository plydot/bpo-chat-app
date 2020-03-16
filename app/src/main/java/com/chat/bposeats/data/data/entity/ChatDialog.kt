package com.chat.bposeats.data.data.entity;

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chat.bposeats.data.data.dao.convert.IMessageConverter
import com.chat.bposeats.data.data.dao.convert.IUserConverter
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser

@Entity(tableName = "Dialogs")
public open class ChatDialog(
    @PrimaryKey var dbId : String,
    var dbDialogPhoto: String?,
    var dbUnreadCount: Int,
    @TypeConverters(IMessageConverter::class) var dbLastMessage: IMessage,
    var dbDialogName: String,
    @TypeConverters(IUserConverter::class) var dbUsers: MutableList<IUser>
): IDialog<IMessage>{
    override fun getDialogPhoto(): String {
        return dbDialogPhoto!!
    }

    override fun getUnreadCount(): Int {
        return dbUnreadCount
    }

    override fun setLastMessage(lastMessage: IMessage?) {
        dbLastMessage = lastMessage!!
    }

    override fun getId(): String {
        return dbId
    }

    override fun getUsers(): MutableList<out IUser> {
        return dbUsers
    }

    override fun getLastMessage(): IMessage {
        return dbLastMessage
    }

    override fun getDialogName(): String {
        return if (dbDialogName.isEmpty()){
            var name = ""
            for (user: IUser in dbUsers){
                name += "${user.name} "
            }
            name
        }else{
            dbDialogName
        }

    }

}