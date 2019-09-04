package test.rido.com.myapplication.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import test.rido.com.myapplication.common.Constants
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    private fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

        retrofit =
            Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit
    }

    fun getApiService(): ApiInterface {
        return getClient().create(ApiInterface::class.java)
    }

}