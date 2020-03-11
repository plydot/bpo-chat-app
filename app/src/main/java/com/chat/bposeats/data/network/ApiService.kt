package com.plydot.sms.bulksms.webservice

import android.annotation.SuppressLint
import com.chat.bposeats.data.network.ApiInterface
import com.chat.bposeats.utils.Constants
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


object HttpService {
    fun service(): ApiInterface {
        val builder: GsonBuilder = GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .setDateFormat(Constants.DATE_DISPLAY_FORMAT)
        builder.create()
        //use unsafe http client for testing
        //TODO: Change this in production environment
        val httpClient = unsafeOkHttpClient
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
            requestBuilder.addHeader("Accept", "application/json")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
        val baseUrl: String = Constants.DEFAULT_URL
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Constants.gson()))
            .client(httpClient.build())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    // Create a trust manager that does not validate certificate chains
    private val unsafeOkHttpClient:
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager
            OkHttpClient.Builder
        get() = try { // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _: String?, _: SSLSession? -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}