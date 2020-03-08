package com.puldroid.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puldroid.mvvm.data.api.GithubClient
import com.puldroid.mvvm.data.models.Repositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    init {
        fetchRepo()
    }

    val repos = MutableLiveData<List<Repositories>>()
    private fun fetchRepo() {
        viewModelScope.launch {
            val res = withContext(Dispatchers.IO) { GithubClient.api.getRepos() }
            if (res.isSuccessful) {
                repos.postValue(res.body())
            }
        }
    }
}