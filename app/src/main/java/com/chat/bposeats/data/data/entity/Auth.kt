package com.chat.bposeats.data.data.entity

import com.google.gson.annotations.SerializedName

data class RegisterData(var phone: String, var password: String,
                    @SerializedName("first_name") var firstName: String,
                    @SerializedName("last_name") var lastName: String)

data class Auth(var phone: String, var password: String) {
}

data class AuthResponse (

    @SerializedName("data") val data : AuthStatus,
    @SerializedName("errors") val errors : List<String>
)

data class AuthStatus (

    @SerializedName("success") val success : Boolean
)