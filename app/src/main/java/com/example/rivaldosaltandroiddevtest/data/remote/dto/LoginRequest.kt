package com.example.rivaldosaltandroiddevtest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
	@kotlinx.serialization.SerialName("password")
	val password: String? = null,
	@kotlinx.serialization.SerialName("username")
	val email: String? = null
)

