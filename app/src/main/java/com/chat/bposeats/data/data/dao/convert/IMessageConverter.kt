package com.chat.bposeats.data.data.dao.convert

import androidx.room.TypeConverter
import com.chat.bposeats.data.data.entity.ChatMessage
import com.chat.bposeats.utils.Constants
import com.stfalcon.chatkit.commons.models.IMessage
import java.io.Serializable


class IMessageConverter : Serializable {
    private val serialVersionUID = -234324324242432342L

    @TypeConverter
    fun fromIMessage(message: ChatMessage?): String? {
        return if (message == null) null else Constants.gson().toJson(message)
    }

    @TypeConverter
    fun toIMessage(dataElementStr: String?): ChatMessage? {
        return if (dataElementStr == null) {
            null
        } else Constants.gson().fromJson(dataElementStr, ChatMessage::class.java)
    }
}