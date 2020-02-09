package com.puldroid.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author aggarwalpulkit596
 */
interface GithubService {

    @GET("users")
    suspend fun getAllUsers() : Response<List<User>>


    @GET("users/{id}")
    suspend fun getMe(@Path("id") id:String = "aggarwalpulkit596") : Response<User>
}