package com.chat.bposeats.data.network

import com.chat.bposeats.data.data.entity.Auth
import com.chat.bposeats.data.data.entity.AuthResponse
import com.plydot.sms.bulksms.webservice.HttpService
import retrofit2.Response
import java.io.IOException


class Routines() {
    private var service: ApiInterface? = null

    init {
        setService()
    }

    private fun setService() {
        service = HttpService.service()
    }

    public fun register(credentials: Auth): AuthResponse?{
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

    public fun checkRegistration(phone: String): Boolean?{
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
}