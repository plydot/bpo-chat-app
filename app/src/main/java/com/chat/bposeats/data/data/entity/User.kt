package com.chat.bposeats.data.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Users", indices = [Index(value = ["phone"], unique = true)])
data class User(
    @PrimaryKey var id: String,
    var username: String,
    var current: Boolean,
    var phone: String,
    var name: String
)