package com.chat.bposeats.data.network

import android.content.Context
import com.chat.bposeats.data.data.entity.*
import com.plydot.sms.bulksms.webservice.HttpService
import retrofit2.Response
import java.io.IOException


class Routines(var context: Context) {
    private var service: ApiInterface? = null

    init {
        setService()
    }

    private fun setService() {
        service = HttpService.service(context)
    }

    fun register(credentials: RegisterData): AuthResponse?{
        return try {
            val response: Response<AuthResponse?> = service!!.register(credentials)!!.execute()
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                throw NullPointerException()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: NullPointerException) {
            e.printStackTrace()
            null
        }
    }

    fun checkRegistration(phone: String): Boolean?{
        return try {
            val response: Response<Boolean?> = service!!.checkRegistration(phone)!!.execute()
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                throw NullPointerException()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: NullPointerException) {
            e.printStackTrace()
            null
        }
    }

    fun getUser(phone: String): User?{
        return try {
            val response: Response<User?> = service!!.getUser(phone)!!.execute()
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                throw NullPointerException()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: NullPointerException) {
            e.printStackTrace()
            null
        }
    }

    fun getToken(auth: Auth): JwtToken?{
        return try {
            val response: Response<JwtToken?> = service!!.getToken(auth)!!.execute()
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                throw NullPointerException()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: NullPointerException) {
            e.printStackTrace()
            null
        }
    }
}