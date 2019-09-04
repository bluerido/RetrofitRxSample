package test.rido.com.myapplication

import android.app.Application
import test.rido.com.myapplication.network.ApiInterface
import test.rido.com.myapplication.network.RetrofitClient

class BaseApplication: Application() {

    companion object {
        lateinit var apiClient: ApiInterface
    }

    override fun onCreate() {
        super.onCreate()

        apiClient = RetrofitClient.getApiService()

    }
}