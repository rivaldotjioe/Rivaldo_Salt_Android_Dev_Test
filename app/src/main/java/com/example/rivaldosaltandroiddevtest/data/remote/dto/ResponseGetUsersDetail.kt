package com.example.rivaldosaltandroiddevtest.data.remote.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseGetUsersDetail(
	val data: DataUser? = null,
	val support: Support? = null
)
@kotlinx.serialization.Serializable
data class Support(
	val text: String? = null,
	val url: String? = null
)
@kotlinx.serialization.Serializable
data class DataUser(
	@SerialName("last_name")
	val lastName: String? = null,
	val id: Int? = null,
	val avatar: String? = null,
	@SerialName("first_name")
	val firstName: String? = null,
	val email: String? = null
)

