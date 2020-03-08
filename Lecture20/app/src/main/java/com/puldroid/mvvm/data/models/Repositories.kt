package com.puldroid.mvvm.data.models

import java.io.Serializable

data class Repositories(
	val forks: Int? = null,
	val builtBy: List<Author>? = null,
	val author: String? = null,
	val name: String? = null,
	val description: String? = null,
	val language: String? = null,
	val avatar: String? = null,
	val languageColor: String? = null,
	val stars: Int? = null,
	val url: String? = null,
	val currentPeriodStars: Int? = null
) : Serializable
data class Author(
	val href: String? = null,
	val avatar: String? = null,
	val username: String? = null
)

