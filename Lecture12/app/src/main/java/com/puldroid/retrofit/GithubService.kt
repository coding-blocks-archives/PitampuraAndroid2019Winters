package com.puldroid.retrofit

import retrofit2.Response
import retrofit2.http.GET

/**
 * @author aggarwalpulkit596
 */
interface GithubService {

    @GET("users")
    suspend fun getAllUsers() : Response<List<User>>
}