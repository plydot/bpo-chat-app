package com.chat.bposeats.data.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey var id: String,
    var username: String
)