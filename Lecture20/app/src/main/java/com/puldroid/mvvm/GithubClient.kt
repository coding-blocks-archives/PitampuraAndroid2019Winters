package com.puldroid.mvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://github-trending-api.now.sh/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}