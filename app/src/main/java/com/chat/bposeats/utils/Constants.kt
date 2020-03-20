package com.chat.bposeats.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder

public class Constants {
    companion object{
        const val DATE_DISPLAY_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val DEFAULT_URL: String = "https://bpo.http.plydot.com"
        const val DEFAULT_SOCKET_URL: String = "https://bb9d3cbe.ngrok.io"
        fun gson():Gson {
            val builder = GsonBuilder()
                    .serializeNulls()
                    .setDateFormat(DATE_DISPLAY_FORMAT)
            return builder.create()
        }
    }
}
