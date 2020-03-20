package com.chat.bposeats.data.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Token")
data class JwtToken (

	@SerializedName("refresh") @PrimaryKey val refresh : String,
	@SerializedName("access") val access : String
)