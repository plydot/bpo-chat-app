package com.chat.bposeats.data.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chat.bposeats.data.data.dao.convert.IUserConverter
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

@Entity(tableName = "ChatMessages")
class ChatMessage(
    @PrimaryKey var dbId : String,
    var name: String,
    var message: String,
    var dateCreated: Date,
    @TypeConverters(IUserConverter::class) var dbUser: IUser
) : IMessage {
    override fun getId(): String {
        return dbId
    }

    override fun getCreatedAt(): Date {
        return dateCreated
    }

    override fun getUser(): IUser {
        return dbUser
    }

    override fun getText(): String {
        return message
    }
}