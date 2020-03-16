package com.chat.bposeats.data.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.stfalcon.chatkit.commons.models.IUser

@Entity(tableName = "Users", indices = [Index(value = ["phone"], unique = true)])
data class User(
    @PrimaryKey var _id: String,
    var username: String,
    var current: Boolean,
    var phone: String,
    var _name: String,
    var _avatar: String
) : IUser {
    override fun getAvatar(): String {
        return _avatar
    }

    override fun getName(): String {
        return _name
    }

    override fun getId(): String {
        return _id
    }
}