package com.example.rivaldosaltandroiddevtest.data.repository

import com.example.rivaldosaltandroiddevtest.data.remote.ApiResponse
import com.example.rivaldosaltandroiddevtest.data.remote.RemoteDataSource
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginRequest
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.domain.repointerface.ILoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepository(val remoteDataSource: RemoteDataSource) : ILoginRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>> {
        return flow {
            var loginRequest = LoginRequest(email = email, password = password)
            remoteDataSource.login(loginRequest = loginRequest).collect { response ->
                when(response) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(data = response.data as LoginResponse))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(message = response.message.toString()))
                    }
                    is ApiResponse.Loading -> {

                    }
                }
            }
        }

    }
}