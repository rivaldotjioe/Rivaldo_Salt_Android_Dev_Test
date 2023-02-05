package com.example.rivaldosaltandroiddevtest.domain.repointerface

import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginRequest
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.domain.Resource
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>>
}