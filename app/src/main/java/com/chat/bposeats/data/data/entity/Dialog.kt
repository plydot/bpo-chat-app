package com.chat.bposeats.data.data.entity;

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chat.bposeats.data.data.dao.convert.IMessageConverter
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser

@Entity(tableName = "Dialogs")
public open class Dialog(
    @PrimaryKey var _id : String,
    var _dialogPhoto: String,
    var _unreadCount: Int,
    @TypeConverters(IMessageConverter::class) var _lastMessage: IMessage,
    var _dialogName: String
): IDialog<IMessage>{
    override fun getDialogPhoto(): String {
        return _dialogPhoto
    }

    override fun getUnreadCount(): Int {
        return _unreadCount
    }

    override fun setLastMessage(message: IMessage?) {
        _lastMessage = message!!
    }

    override fun getId(): String {
        return _id
    }

    override fun getUsers(): MutableList<out IUser> {
        TODO("Not yet implemented")
    }

    override fun getLastMessage(): IMessage {
        return _lastMessage
    }

    override fun getDialogName(): String {
        return _dialogName
    }

}