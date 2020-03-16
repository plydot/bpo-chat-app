package com.chat.bposeats.data.data.entity

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

class ChatMessage(var userId : String, var name: String, var phone: String, var message: String) : IMessage {
    override fun getId(): String {
        return UUID.randomUUID().toString()
    }

    override fun getCreatedAt(): Date {
        return Date()
    }

    override fun getUser(): IUser {
        return User(userId,
            name, true,
            phone, name, "")
    }

    override fun getText(): String {
        return message
    }
}