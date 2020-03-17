package com.chat.bposeats.data.data.dao.convert

import androidx.room.TypeConverter
import com.chat.bposeats.data.data.entity.User
import com.chat.bposeats.utils.Constants
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable


class IUserConverter : Serializable {
    private val serialVersionUID = -234324324242432342L

    @TypeConverter
    fun fromIUser(user: User?): String? {
        return if (user == null) null else Constants.gson().toJson(user)
    }

    @TypeConverter
    fun toIUser(dataElementStr: String?): User? {
        return if (dataElementStr == null) {
            null
        } else Constants.gson().fromJson(dataElementStr, User::class.java)
    }
}