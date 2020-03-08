package com.puldroid.mvvm.data.repos

import com.puldroid.mvvm.data.api.GithubClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MainRepository {

    suspend fun getRepos() = withContext(Dispatchers.IO){GithubClient.api.getRepos()}
}