package com.chat.bposeats.data.data.dao.convert

import androidx.room.TypeConverter
import com.chat.bposeats.utils.Constants
import java.io.Serializable
import java.util.*

class DateConverter: Serializable {
    private val serialVersionUID = -234324324260432342L

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return if (date == null) null else Constants.gson().toJson(date)
    }

    @TypeConverter
    fun toDate(dataElementStr: String?): Date? {
        return if (dataElementStr == null) {
            null
        } else Constants.gson().fromJson(dataElementStr, Date::class.java)
    }
}