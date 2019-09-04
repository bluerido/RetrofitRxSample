package test.rido.com.myapplication.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import test.rido.com.myapplication.network.data.User

interface ApiInterface {

    @GET("/search/users")
    fun searchUser(@Query("q") user: String): Observable<User>
}