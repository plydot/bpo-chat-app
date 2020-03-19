package com.chat.bposeats.data.network

import com.chat.bposeats.data.data.entity.*
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

    @GET("/api/users/{phone}/")
    fun getUser(@Path("phone") phone: String) : Call<User?>?

    @POST("/api/token/")
    fun getToken(@Body auth: Auth) : Call<JwtToken?>?

}