package com.example.rivaldosaltandroiddevtest.data.remote

import androidx.compose.foundation.isSystemInDarkTheme
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginRequest
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import io.ktor.http.cio.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val reqresApi: ReqresService) {

    suspend fun login(loginRequest: LoginRequest) : Flow<ApiResponse<LoginResponse>> {
        return flow {
            emit(ApiResponse.Loading(data = null))
            try {
                val responseLogin = reqresApi.login(loginRequest = loginRequest)
                when(responseLogin) {
                    is ApiResponse.Success -> {
                        emit(ApiResponse.Success(data = responseLogin.data as LoginResponse))
                    }
                    is ApiResponse.Error -> {
                        emit(ApiResponse.Error(message = responseLogin.message.toString(), data = null))
                    }
                    is ApiResponse.Loading -> {

                    }
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(message = e.message ?: "Error Occurred!", data = null))
            }
        }
    }

    suspend fun getUserById(id: Int) : Flow<ApiResponse<ResponseGetUsersDetail>> {
        return flow {
            emit(ApiResponse.Loading(data = null))
            try {
                val response = reqresApi.getUserDetail(id = id)
                when(response) {
                    is ApiResponse.Success -> {
                        emit(ApiResponse.Success(data = response.data as ResponseGetUsersDetail))
                    }
                    is ApiResponse.Error -> {
                        emit(ApiResponse.Error(message = response.message.toString(), data = null))
                    }
                    is ApiResponse.Loading -> {

                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(message = e.message ?: "Error Occurred!", data = null))
            }
        }
    }
}