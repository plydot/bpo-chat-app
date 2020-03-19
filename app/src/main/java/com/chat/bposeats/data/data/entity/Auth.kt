package com.chat.bposeats.data.data.entity

import com.google.gson.annotations.SerializedName

data class Auth(var phone: String, var password: String) {
}

data class AuthResponse (

    @SerializedName("data") val data : AuthStatus,
    @SerializedName("errors") val errors : List<String>
)

data class AuthStatus (

    @SerializedName("success") val success : Boolean
)