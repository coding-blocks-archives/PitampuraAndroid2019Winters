package com.puldroid.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * @author aggarwalpulkit596
 */
object Client {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create<GithubService>()
}