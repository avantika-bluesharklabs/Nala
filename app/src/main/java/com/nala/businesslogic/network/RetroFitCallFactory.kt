package com.nala.businesslogic.network


import com.nala.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Avantika Gadhiya on 3/31/2020.
 */
class RetroFitCallFactory {
    companion object {
        private const val NETWORK_CALL_TIMEOUT: Long = 600

        fun create(): RetroFitInterface {
            return getRetrofit().create(RetroFitInterface::class.java)
        }

        private fun getRetrofit(): Retrofit {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            builder.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            builder.followRedirects(false)

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }

            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
        }
    }
}