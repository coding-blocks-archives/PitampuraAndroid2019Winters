package com.puldroid.retrofit

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("node_id")
    val name: String,
    val login: String,
    val gistsUrl: String,
    val reposUrl: String,
    val followingUrl: String,
    val receivedEventsUrl: String,
    val followers: Int,
    val avatarUrl: String
)
