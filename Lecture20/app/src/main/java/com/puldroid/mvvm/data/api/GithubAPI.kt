package com.puldroid.mvvm.data.api

import com.puldroid.mvvm.data.models.Repositories
import retrofit2.Response
import retrofit2.http.GET

interface GithubAPI {

    @GET("repositories")
    suspend fun getRepos():Response<List<Repositories>>
}