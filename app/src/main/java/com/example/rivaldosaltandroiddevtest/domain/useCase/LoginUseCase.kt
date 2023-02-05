package com.example.rivaldosaltandroiddevtest.domain.useCase

import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.domain.Resource
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>>
}