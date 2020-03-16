package com.chat.bposeats.data.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.stfalcon.chatkit.commons.models.IUser

@Entity(tableName = "Users", indices = [Index(value = ["phone"], unique = true)])
data class User(
    @PrimaryKey var dbId: String,
    var username: String,
    var current: Boolean,
    var phone: String,
    var dbName: String,
    var dbAvatar: String
) : IUser {
    override fun getAvatar(): String {
        return dbAvatar
    }

    override fun getName(): String {
        return dbName
    }

    override fun getId(): String {
        return dbId
    }
}