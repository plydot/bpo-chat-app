package com.chat.bposeats.data.data.dao.convert

import androidx.room.TypeConverter
import com.chat.bposeats.data.data.entity.User
import com.chat.bposeats.utils.Constants
import com.google.gson.reflect.TypeToken
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable


class IUserListConverter : Serializable {
    private val serialVersionUID = -234324324242432343L

    @TypeConverter
    fun fromIUserList(dataElements: MutableList<User?>?): String? {
        return if (dataElements == null) null else Constants.gson().toJson(
            dataElements,
            object : TypeToken<MutableList<User?>?>() {}.type
        )
    }

    @TypeConverter
    fun toIUserList(dataElementStr: String?): MutableList<User?>? {
        return if (dataElementStr == null) {
            ArrayList()
        } else Constants.gson().fromJson(
            dataElementStr,
            object : TypeToken<MutableList<User?>?>() {}.type
        )
    }
}