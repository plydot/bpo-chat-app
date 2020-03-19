package com.chat.bposeats.data.network

import com.chat.bposeats.data.data.entity.Auth
import com.chat.bposeats.data.data.entity.AuthResponse
import com.chat.bposeats.data.data.entity.RegisterData
import com.chat.bposeats.data.data.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("api/auth/")
    fun register(@Body credentials: RegisterData): Call<AuthResponse?>?

    @GET("api/auth/{phone}/")
    fun checkRegistration(@Path("phone") phone: String) : Call<Boolean?>?

    @POST("/api/users/{phone}/")
    fun getUser(@Path("phone") phone: String) : Call<User?>?

}