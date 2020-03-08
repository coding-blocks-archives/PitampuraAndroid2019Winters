package com.puldroid.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puldroid.mvvm.data.models.Repositories
import com.puldroid.mvvm.data.repos.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    init {
        fetchRepo()
    }

    val repos = MutableLiveData<List<Repositories>>()
    private fun fetchRepo(isPremium: Boolean = false) {
        viewModelScope.launch {
            val res = MainRepository.getRepos()
            if (res.isSuccessful) {
                if (isPremium)
                    repos.postValue(res.body()?.subList(0,10))
                else{
                    repos.postValue(res.body())
                }
            }
        }
    }

    private fun fetchTop10Repo() {
        viewModelScope.launch {
            val res = MainRepository.getRepos()
            if (res.isSuccessful) {
                repos.postValue(res.body()?.subList(0, 10))
            }
        }
    }
}