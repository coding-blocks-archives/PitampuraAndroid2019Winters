package com.puldroid.mvvm

import retrofit2.http.GET

interface GithubAPI {

    @GET("repositories")
    suspend fun getRepos():List<Repositories>
}