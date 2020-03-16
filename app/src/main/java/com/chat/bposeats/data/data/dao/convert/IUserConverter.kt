package com.chat.bposeats.data.data.dao.convert

import androidx.room.TypeConverter
import com.chat.bposeats.utils.Constants
import com.google.gson.reflect.TypeToken
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable


class IUserConverter : Serializable {
    private val serialVersionUID = -234324324242432343L

    @TypeConverter
    fun fromIUserList(dataElements: MutableList<IUser?>?): String? {
        return if (dataElements == null) null else Constants.gson().toJson(
            dataElements,
            object : TypeToken<MutableList<IUser?>?>() {}.type
        )
    }

    @TypeConverter
    fun toIUserList(dataElementStr: String?): MutableList<IUser?>? {
        return if (dataElementStr == null) {
            ArrayList()
        } else Constants.gson().fromJson(
            dataElementStr,
            object : TypeToken<MutableList<IUser?>?>() {}.type
        )
    }
}