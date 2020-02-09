package com.puldroid.retrofit

data class User(
    val name: String,
    val login: String,
    val gistsUrl: String,
    val reposUrl: String,
    val followingUrl: String,
    val receivedEventsUrl: String,
    val followers: Int,
    val avatar_url: String
)
